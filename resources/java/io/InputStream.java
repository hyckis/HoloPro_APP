/* (C) Copyright 2001 Sun Microsystems, Inc. 
 * (C) Copyright 2001 Open Services Gateway Initiative, Inc. (the OSGi alliance)
 */

/* $Header: /cvshome/repository/ee/minimum/java/io/InputStream.java,v 1.2 2003/03/12 03:17:39 hargrave Exp $ */

package java.io;
public abstract class InputStream {
    public InputStream() { }
    public int available() throws java.io.IOException { return 0; }
    public void close() throws java.io.IOException { }
    public void mark(int var0) { }
    public boolean markSupported() { return false; }
    public abstract int read() throws java.io.IOException;
    public int read(byte[] var0) throws java.io.IOException { return 0; }
    public int read(byte[] var0, int var1, int var2) throws java.io.IOException { return 0; }
    public void reset() throws java.io.IOException { }
    public long skip(long var0) throws java.io.IOException { return 0l; }
}

