/* (C) Copyright 2001 Sun Microsystems, Inc. 
 * (C) Copyright 2001 Open Services Gateway Initiative, Inc. (the OSGi alliance)
 */

/* $Header: /cvshome/repository/ee/minimum/java/io/FileOutputStream.java,v 1.2 2003/03/12 03:17:39 hargrave Exp $ */

package java.io;
public class FileOutputStream extends java.io.OutputStream {
    public FileOutputStream(java.io.File var0) throws java.io.FileNotFoundException { }
    public FileOutputStream(java.io.FileDescriptor var0) { }
    public FileOutputStream(java.lang.String var0) throws java.io.FileNotFoundException { }
    public FileOutputStream(java.lang.String var0, boolean var1) throws java.io.FileNotFoundException { }
    public void close() throws java.io.IOException { }
    protected void finalize() throws java.io.IOException { }
    public final java.io.FileDescriptor getFD() throws java.io.IOException { return null; }
    public void write(byte[] var0) throws java.io.IOException { }
    public void write(byte[] var0, int var1, int var2) throws java.io.IOException { }
    public void write(int var0) throws java.io.IOException { }
}

