package org.zplet.iff;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class IFFChunk {
	public static String toString(byte bytes[]) {
		final Charset charset = Charset.forName("US-ASCII");

		assert bytes.length == 4;

		return charset.decode(ByteBuffer.wrap(bytes)).toString();
	}

	public static byte[] fromString(String s) {
		final Charset charset = Charset.forName("US-ASCII");

		ByteBuffer buf = charset.encode(s.substring(0, 4));

		return buf.array();
	}

}
