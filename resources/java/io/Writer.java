/* (C) Copyright 2001 Sun Microsystems, Inc. 
 * (C) Copyright 2001 Open Services Gateway Initiative, Inc. (the OSGi alliance)
 */

/* $Header: /cvshome/repository/ee/minimum/java/io/Writer.java,v 1.2 2003/03/12 03:17:39 hargrave Exp $ */

package java.io;
public abstract class Writer {
    protected Writer() { }
    protected Writer(java.lang.Object var0) { }
    public abstract void close() throws java.io.IOException;
    public abstract void flush() throws java.io.IOException;
    public void write(char[] var0) throws java.io.IOException { }
    public abstract void write(char[] var0, int var1, int var2) throws java.io.IOException;
    public void write(int var0) throws java.io.IOException { }
    public void write(java.lang.String var0) throws java.io.IOException { }
    public void write(java.lang.String var0, int var1, int var2) throws java.io.IOException { }
    protected java.lang.Object lock;
}

