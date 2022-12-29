/* (C) Copyright 2001 Sun Microsystems, Inc. 
 * (C) Copyright 2001 Open Services Gateway Initiative, Inc. (the OSGi alliance)
 */

/* $Header: /cvshome/repository/ee/minimum/java/io/BufferedOutputStream.java,v 1.2 2003/03/12 03:17:39 hargrave Exp $ */

package java.io;
public class BufferedOutputStream extends java.io.FilterOutputStream {
    public BufferedOutputStream(java.io.OutputStream var0) { super(null); }
    public BufferedOutputStream(java.io.OutputStream var0, int var1) { super(null); }
    public void flush() throws java.io.IOException { }
    public void write(byte[] var0, int var1, int var2) throws java.io.IOException { }
    public void write(int var0) throws java.io.IOException { }
    protected byte[] buf;
    protected int count;
}

