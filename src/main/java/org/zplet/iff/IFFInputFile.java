package org.zplet.iff;

import java.io.*;
import java.util.*;

public class IFFInputFile extends IFFFile {
	private Stack<Long> openchunkends;

	public IFFInputFile(File file) throws IOException {
		super(file, "r");
		openchunkends = new Stack<Long>();
	}

	public IFFInputFile(String name) throws IOException {
		super(name, "r");
		openchunkends = new Stack<Long>();
	}

	public synchronized IFFChunkInfo readChunkInfo() throws IOException {
		IFFChunkInfo result = new IFFChunkInfo();
		byte chunktype[] = new byte[4];
		long chunkbegin;

		read(chunktype, 0, 4);
		chunkbegin = getFilePointer();
		result.chunktype = IFFChunk.toString(chunktype);
		result.chunklength = readInt();
		openchunks.push(chunkbegin);
		openchunkends.push(getFilePointer() + result.chunklength);

		return result;
	}

	public synchronized IFFChunkInfo skipToChunk(String type)
			throws IOException, IFFChunkNotFoundException {
		IFFChunkInfo chunkinfo;

		if (getFilePointer() >= (openchunkends.peek()).longValue())
			throw new IFFChunkNotFoundException("Chunk " + type
					+ " not found at current level");
		chunkinfo = readChunkInfo();
		while (!chunkinfo.chunktype.equals(type)) {
			closeChunk();
			if (getFilePointer() >= (openchunkends.peek()).longValue())
				throw new IFFChunkNotFoundException("Chunk " + type
						+ " not found at current level");
			chunkinfo = readChunkInfo();
		}
		return chunkinfo;
	}

	public synchronized String readFORM() throws IOException {
		IFFChunkInfo formchunkinfo;
		byte subtype[] = new byte[4];

		formchunkinfo = readChunkInfo();
		if (formchunkinfo.chunktype.equals("FORM")) {
			read(subtype, 0, 4);
		} else {
			// throw new Exception("That's not a FORM!");
		}
		return IFFChunk.toString(subtype);
	}

	public synchronized void closeChunk() throws IOException {
		long chunkend;

		chunkend = ((openchunkends.pop()).longValue() + 1) & ~1L;
		openchunks.pop();
		// doing the seek last ensures exceptions leave stacks consistent
		seek(chunkend);
	}

	@Override
	public synchronized void close() throws IOException {
		while (!openchunks.empty()) {
			try {
				closeChunk();
			} catch (IOException ioexcpt) {
				// Ignore seek errors probably caused by opening a bad chunk
			}
		}
		super.close();
	}

}
