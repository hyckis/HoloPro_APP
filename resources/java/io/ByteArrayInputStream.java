/* (C) Copyright 2001 Sun Microsystems, Inc. 
 * (C) Copyright 2001 Open Services Gateway Initiative, Inc. (the OSGi alliance)
 */

/* $Header: /cvshome/repository/ee/minimum/java/io/ByteArrayInputStream.java,v 1.2 2003/03/12 03:17:39 hargrave Exp $ */

package java.io;
public class ByteArrayInputStream extends java.io.InputStream {
    public ByteArrayInputStream(byte[] var0) { }
    public ByteArrayInputStream(byte[] var0, int var1, int var2) { }
    public int available() { return 0; }
    public void close() throws java.io.IOException { }
    public void mark(int var0) { }
    public boolean markSupported() { return false; }
    public int read() { return 0; }
    public int read(byte[] var0, int var1, int var2) { return 0; }
    public void reset() { }
    public long skip(long var0) { return 0l; }
    protected byte[] buf;
    protected int pos;
    protected int mark;
    protected int count;
}

