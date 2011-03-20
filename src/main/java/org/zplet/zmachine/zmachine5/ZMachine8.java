/* Zplet, a Z-Machine interpreter in Java */
/* Copyright 1996,2001 Matthew T. Russotto */
/* As of 23 February 2001, this code is open source and covered by the */
/* Artistic License, found within this package */

package org.zplet.zmachine.zmachine5;

import org.zplet.screenmodel.ZScreen;

public class ZMachine8 extends ZMachine5 {
	public ZMachine8(ZScreen screen, byte[] memory_image) {
		super(screen, memory_image);
	}

	@Override
	public int string_address(short addr) {
		return ((addr) & 0xFFFF) << 3;
	}

	@Override
	public int routine_address(short addr) {
		return ((addr) & 0xFFFF) << 3;
	}
}
