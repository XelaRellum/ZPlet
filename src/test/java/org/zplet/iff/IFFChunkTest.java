package org.zplet.iff;

import static org.junit.Assert.*;

import org.junit.Test;
import org.zplet.iff.IFFChunk;


public class IFFChunkTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testToStringDeprecated()
	{
		byte bytes[] = {65, 66, 67, 68};
		
		assertEquals(new String(bytes, 0), IFFChunk.toString(bytes));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testToStringEmpty()
	{
		byte bytes[] = {0, 0, 0, 0};

		assertEquals(new String(bytes, 0), IFFChunk.toString(bytes));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testToStringWithZero()
	{
		byte bytes[] = {65, 0, 0, 0};
		
		assertEquals(new String(bytes, 0), IFFChunk.toString(bytes));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testFromString()
	{
		byte expected[] = new byte[4];
		"0123".getBytes(0, 4, expected, 0);
		assertArrayEquals(expected, IFFChunk.fromString("0123"));
	}
	
	@SuppressWarnings("deprecation")
	@Test(expected = StringIndexOutOfBoundsException.class)
	public void testFromTooShortStringDeprecated()
	{
		byte expected[] = {48,0,0,0};
		byte actual[] = new byte[4];
		"0".getBytes(0, 4, actual, 0);
		assertArrayEquals(expected, actual);
	}

	@Test(expected = StringIndexOutOfBoundsException.class)
	public void testFromTooShortString()
	{
		byte expected[] = {48,0,0,0};
		assertArrayEquals(expected, IFFChunk.fromString("0"));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testFromTooLongString()
	{
		String given = "012345678";
		byte expected[] = new byte[4];
		given.getBytes(0, 4, expected, 0);
		assertArrayEquals(expected, IFFChunk.fromString(given));
	}
}
