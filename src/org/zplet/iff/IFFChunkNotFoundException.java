package org.zplet.iff;

public
class IFFChunkNotFoundException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructs an IFFChunkNotFoundException with no detail message.
     * A detail message is a String that describes this particular exception.
     */
    public IFFChunkNotFoundException() {
		super();
    }

    /**
     * Constructs an IFFChunkNotFoundException with the specified detail message.
     * A detail message is a String that describes this particular exception.
     * @param s the detail message
     */
    public IFFChunkNotFoundException(String s) {
		super(s);
    }
}
