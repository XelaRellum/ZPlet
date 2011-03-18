/* Zplet, a Z-Machine interpreter in Java */
/* Copyright 1996,2001 Matthew T. Russotto */
/* As of 23 February 2001, this code is open source and covered by the */
/* Artistic License, found within this package */

package russotto.zplet.screenmodel;

import java.util.*;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.applet.Applet;

 class ZCursor {
		Color cursorcolor, bgcolor;
		boolean shown;
		int t,l,w,h;
		Component parent;
		
		ZCursor(Color cursorcolor, Color bgcolor, Component parent) {
				shown = false;
				this.cursorcolor = cursorcolor;
				this.bgcolor = bgcolor;
				this.parent = parent;
		}

		ZCursor(Component parent) {
				this(Color.green, Color.yellow, parent);
		}

		ZCursor() {
				this(Color.green, Color.yellow, null);
		}

		synchronized void show() {
			Graphics g;
			
			if (!shown) {
				shown = true;
				if (parent != null) {
					g = parent.getGraphics();
					if (g != null) {
						g.setColor(cursorcolor);
						g.fillRect(l,t,w,h);
					}
				}
			}
		}

		synchronized void hide() {
			if (shown) {
				shown = false;
				if (parent != null) {
					parent.repaint(l,t,w,h);
//					g.setColor(bgcolor);
//					g.fillRect(l,t,w,h);
				}
			}
		}

		synchronized void redraw(Graphics g) {
			if (shown) {
				g.setColor(cursorcolor);
				g.fillRect(l,t,w,h);
			}
		}

		synchronized void move(int l, int t) {
			boolean wasshown = shown;

			if (wasshown)
					hide();
			this.l = l;
			this.t = t;
			if (wasshown)
					show();
		}

		synchronized void size(int w, int h) {
				boolean wasshown = shown;

				if (wasshown)
						hide();
				this.w = w;
				this.h = h;
				if (wasshown)
						show();
		}

//		synchronized void setGraphics(Graphics g) {
//				boolean wasshown = shown;
//
//				this.g = g;
//				g.setColor(cursorcolor);
//				g.setXORMode(bgcolor);
//				if (wasshown)
//						show();
//		}

		synchronized void setcolors(Color cursorcolor, Color bgcolor) {
				boolean wasshown = shown;

				if (wasshown)
					hide();
				this.cursorcolor = cursorcolor;
				this.bgcolor = bgcolor;
				if (wasshown)
					show();
		}
 }

