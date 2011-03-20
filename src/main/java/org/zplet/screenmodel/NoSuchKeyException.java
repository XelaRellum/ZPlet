/* Zplet, a Z-Machine interpreter in Java */
/* Copyright 1996,2001 Matthew T. Russotto */
/* As of 23 February 2001, this code is open source and covered by the */
/* Artistic License, found within this package */

package org.zplet.screenmodel;

class NoSuchKeyException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSuchKeyException() {
		super();
	}

	public NoSuchKeyException(String s) {
		super(s);
	}
}
