package russotto.iff;
import java.io.*;
import java.util.Stack;

public class IFFFile
		extends RandomAccessFile
{
    protected Stack<Long> openchunks;
    
    public IFFFile(String name, String mode) throws IOException
    {
		super(name, mode);
		openchunks = new Stack<Long>();
    }

    public IFFFile(File file, String mode) throws IOException
    {
		super(file, mode);
		openchunks = new Stack<Long>();
    }

    public void chunkSeek(int offset) throws IOException
    {
    	seek((openchunks.peek()).longValue() + 4 + offset);
    }
    
    public int getChunkPointer() throws IOException
    {
    	return (int)getFilePointer() - (int)(openchunks.peek()).longValue() - 4;
    }
}

