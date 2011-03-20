package org.zplet.iff;

import java.io.*;

public class IFFOutputFile extends IFFFile {
	public IFFOutputFile(File file) throws IOException {
		super(file, "rw");
	}

	public IFFOutputFile(File file, String type) throws IOException {
		this(file);
		openChunk("FORM");
		write(getOSType(type), 0, 4);
	}

	public IFFOutputFile(String name) throws IOException {
		super(name, "rw");
	}

	public IFFOutputFile(String name, String type) throws IOException {
		this(name);
		openChunk("FORM");
		write(getOSType(type), 0, 4);
	}

	private byte[] getOSType(String s) {
		return IFFChunk.fromString(s);
	}

	public synchronized void openChunk(String type) throws IOException {
		write(getOSType(type), 0, 4);
		openchunks.push(new Long(getFilePointer()));
		writeInt(0);
	}

	public synchronized void closeChunk() throws IOException {
		long location, currentlocation;
		int chunklength;

		currentlocation = getFilePointer();
		chunklength = getChunkPointer();
		location = (openchunks.pop()).longValue();
		seek(location);
		writeInt(chunklength);
		seek(currentlocation);
		if ((chunklength & 1) == 1) {
			writeByte(0);
		}
	}

	@Override
	public synchronized void close() throws IOException {
		while (!openchunks.empty())
			closeChunk();
		super.close();
	}
}
