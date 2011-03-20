/* Zplet, a Z-Machine interpreter in Java */
/* Copyright 1996,2001 Matthew T. Russotto */
/* As of 23 February 2001, this code is open source and covered by the */
/* Artistic License, found within this package */

package org.zplet.awt.screenmodel;

import java.awt.*;

public class ZStatus extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean timegame;
	boolean initialized;
	boolean chronograph;
	String location;
	int score;
	int turns;
	int hours;
	int minutes;
	Label Right;
	Label Left;

	public ZStatus() {
		setLayout(new BorderLayout());
		Right = new Label();
		add("East", Right);
		Left = new Label();
		add("West", Left);
		chronograph = false;
	}

	/*
	 * public boolean gotFocus(Event evt, Object what) {
	 * System.err.println("ZStatus got focus"); return false; }
	 * 
	 * public boolean lostFocus(Event evt, Object what) {
	 * System.err.println("ZStatus lost focus"); return false; }
	 */

	public void update_score_line(String location, int score, int turns) {
		this.timegame = false;
		this.location = location;
		this.score = score;
		this.turns = turns;
		Left.setText(location);
		Right.setText(score + "/" + turns);
		doLayout();
		repaint();
	}

	public void update_time_line(String location, int hours, int minutes) {
		String meridiem;

		this.timegame = true;
		this.location = location;
		this.hours = hours;
		this.minutes = minutes;
		Left.setText(location);
		if (chronograph) {
			Right.setText(hours + ":" + minutes);
		} else {
			if (hours < 12)
				meridiem = "AM";
			else
				meridiem = "PM";
			hours %= 12;
			if (hours == 0)
				hours = 12;
			Right.setText(hours + ":" + minutes + meridiem);
		}
		doLayout();
		repaint();
	}

	@Override
	public Dimension minimumSize() {
		return new Dimension(100, 10);
	}

	@Override
	public Dimension preferredSize() {
		return new Dimension(500, 20);
	}
}
