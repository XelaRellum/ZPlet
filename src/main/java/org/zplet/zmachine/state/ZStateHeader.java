/* Zplet, a Z-Machine interpreter in Java */
/* Copyright 1996,2001 Matthew T. Russotto */
/* As of 23 February 2001, this code is open source and covered by the */
/* Artistic License, found within this package */

package org.zplet.zmachine.state;

import org.zplet.zmachine.ZHeader;

class ZStateHeader extends ZHeader {
	ZStateHeader(byte[] memory_image) {
		this.memory_image = memory_image;
	}

	/* yes, a kludge */
	@Override
	public int file_length() {
		return 0;
	}
}
