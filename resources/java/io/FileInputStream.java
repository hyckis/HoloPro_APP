/* (C) Copyright 2001 Sun Microsystems, Inc. 
 * (C) Copyright 2001 Open Services Gateway Initiative, Inc. (the OSGi alliance)
 */

/* $Header: /cvshome/repository/ee/minimum/java/io/FileInputStream.java,v 1.2 2003/03/12 03:17:39 hargrave Exp $ */

package java.io;
public class FileInputStream extends java.io.InputStream {
    public FileInputStream(java.io.File var0) throws java.io.FileNotFoundException { }
    public FileInputStream(java.io.FileDescriptor var0) { }
    public FileInputStream(java.lang.String var0) throws java.io.FileNotFoundException { }
    public int available() throws java.io.IOException { return 0; }
    public void close() throws java.io.IOException { }
    protected void finalize() throws java.io.IOException { }
    public final java.io.FileDescriptor getFD() throws java.io.IOException { return null; }
    public int read() throws java.io.IOException { return 0; }
    public int read(byte[] var0) throws java.io.IOException { return 0; }
    public int read(byte[] var0, int var1, int var2) throws java.io.IOException { return 0; }
    public long skip(long var0) throws java.io.IOException { return 0l; }
}

