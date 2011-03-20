/* Zplet, a Z-Machine interpreter in Java */
/* Copyright 1996,2001 Matthew T. Russotto */
/* As of 23 February 2001, this code is open source and covered by the */
/* Artistic License, found within this package */

package org.zplet;

import java.awt.*;
import java.net.*;
import java.io.*;

import org.zplet.screenmodel.*;
import org.zplet.zmachine.*;
import org.zplet.zmachine.zmachine3.ZMachine3;
import org.zplet.zmachine.zmachine5.ZMachine5;
import org.zplet.zmachine.zmachine5.ZMachine8;

public class Zplet extends java.applet.Applet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ZScreen screen;
	ZStatus status_line;
	ZMachine zm;

	boolean failed = false;

	@Override
	public void init() {
		String statusfg, statusbg, mainfg, mainbg;
		String font_family, font_size;
		statusfg = getParameter("StatusForeground");
		statusbg = getParameter("StatusBackground");
		mainfg = getParameter("Foreground");
		mainbg = getParameter("Background");
		font_family = getParameter("FontFamily");
		font_size = getParameter("FontSize");
		if (font_family == null) {
			font_family = ZScreen.DEFAULT_FONT_FAMILY;
		}
		setLayout(new BorderLayout());
		screen = new ZScreen();
		status_line = new ZStatus();
		/*
		 * Setting an int from a string can be problematic. So encapsulate in a
		 * try/catch block. All errors are ignored and default size is used.
		 */
		try {
			if (font_size == null) {
				throw new NumberFormatException();
			}
			screen.setFixedFont(font_family, Integer.valueOf(font_size));
		} catch (NumberFormatException e) {
			screen.setFixedFont(font_family, ZScreen.DEFAULT_FONT_SIZE);
		}
		status_line.setForeground(Color.black);
		status_line.setBackground(Color.white);
		screen.setZForeground(ZColor.Z_WHITE);
		screen.setZBackground(ZColor.Z_BLACK);
		if (statusfg != null)
			status_line.setForeground(ZColor.getcolor(statusfg));

		if (statusbg != null)
			status_line.setBackground(ZColor.getcolor(statusbg));

		if (mainfg != null)
			screen.setZForeground(ZColor.getcolornumber(mainfg));
		if (mainbg != null)
			screen.setZBackground(ZColor.getcolornumber(mainbg));

		add("North", status_line);
		add("Center", screen);

	}

	void startzm() {
		URL myzfile;
		InputStream myzstream;
		String zcodefile;

		byte zmemimage[];

		zmemimage = null;
		try {
			zcodefile = getParameter("StoryFile");
			myzfile = new URL(getDocumentBase(), zcodefile);
			// System.err.println(myzfile);
			myzstream = myzfile.openStream();
			zmemimage = suckstream(myzstream);
		} catch (MalformedURLException booga) {
			add("North", new Label("Malformed URL"));
			failed = true;
		} catch (IOException booga) {
			add("North", new Label("I/O Error"));
			/* don't set failed, may want to retry */
		}
		if (zmemimage != null) {
			switch (zmemimage[0]) {
			case 3:
				zm = new ZMachine3(screen, status_line, zmemimage);
				break;
			case 5:
				remove(status_line);
				zm = new ZMachine5(screen, zmemimage);
				break;
			case 8:
				remove(status_line);
				zm = new ZMachine8(screen, zmemimage);
				break;
			default:
				add("North", new Label("Not a valid V3,V5, or V8 story file"));
			}
			if (zm != null)
				zm.start();
		}
	}

	byte[] suckstream(InputStream mystream) throws IOException {
		byte buffer[];
		byte oldbuffer[];
		int currentbytes = 0;
		int bytesleft;
		int got;
		int buffersize = 2048;

		buffer = new byte[buffersize];
		bytesleft = buffersize;
		got = 0;
		while (got != -1) {
			bytesleft -= got;
			currentbytes += got;
			if (bytesleft == 0) {
				oldbuffer = buffer;
				buffer = new byte[buffersize + currentbytes];
				System.arraycopy(oldbuffer, 0, buffer, 0, currentbytes);
				oldbuffer = null;
				bytesleft = buffersize;
			}
			got = mystream.read(buffer, currentbytes, bytesleft);
		}
		if (buffer.length != currentbytes) {
			oldbuffer = buffer;
			buffer = new byte[currentbytes];
			System.arraycopy(oldbuffer, 0, buffer, 0, currentbytes);
		}
		return buffer;
	}

	/*
	 * public boolean gotFocus(Event evt, Object what) {
	 * System.err.println("ZPlet got focus"); return false; }
	 * 
	 * public boolean lostFocus(Event evt, Object what) {
	 * System.err.println("ZPlet lost focus"); return false; }
	 */
	@Override
	public void start() {
		if (!failed && ((zm == null) || !zm.isAlive())) {
			startzm();
		}

	}

	@Override
	public void destroy() {
		if (zm != null)
			zm.request_stop();
		zm = null;
		remove(screen);
		screen = null;
	}
}
