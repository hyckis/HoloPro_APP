/* (C) Copyright 2001 Sun Microsystems, Inc. 
 * (C) Copyright 2001 Open Services Gateway Initiative, Inc. (the OSGi alliance)
 */

/* $Header: /cvshome/repository/ee/minimum/java/io/PrintStream.java,v 1.2 2003/03/12 03:17:39 hargrave Exp $ */

package java.io;
public class PrintStream extends java.io.FilterOutputStream {
    public PrintStream(java.io.OutputStream var0) { super(null); }
    public PrintStream(java.io.OutputStream var0, boolean var1) { super(null); }
    public boolean checkError() { return false; }
    public void close() { }
    public void flush() { }
    public void print(char[] var0) { }
    public void print(char var0) { }
    public void print(double var0) { }
    public void print(float var0) { }
    public void print(int var0) { }
    public void print(long var0) { }
    public void print(java.lang.Object var0) { }
    public void print(java.lang.String var0) { }
    public void print(boolean var0) { }
    public void println() { }
    public void println(char[] var0) { }
    public void println(char var0) { }
    public void println(double var0) { }
    public void println(float var0) { }
    public void println(int var0) { }
    public void println(long var0) { }
    public void println(java.lang.Object var0) { }
    public void println(java.lang.String var0) { }
    public void println(boolean var0) { }
    protected void setError() { }
    public void write(byte[] var0, int var1, int var2) { }
    public void write(int var0) { }
}

