/* Zplet, a Z-Machine interpreter in Java */
/* Copyright 1996,2001 Matthew T. Russotto */
/* As of 23 February 2001, this code is open source and covered by the */
/* Artistic License, found within this package */

package org.zplet.awt.screenmodel;

import java.util.*;

class SyncVector extends Vector<Object> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SyncVector() {
		super();
	}

	public synchronized Object syncPopFirstElement() {
		Object first = syncFirstElement();
		if (first != null)
			removeElementAt(0);
		return first;
	}

	public synchronized Object syncFirstElement() {
		Object first = null;
		try {
			first = super.firstElement();
		} catch (NoSuchElementException booga) {
		}
		try {
			if (first == null)
				wait();
			else
				return first;
		} catch (InterruptedException booga) {
		}
		return null;
	}

	public synchronized void syncAddElement(Object obj) {
		super.addElement(obj);
		notify();
	}
}
