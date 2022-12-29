/* (C) Copyright 2001 Sun Microsystems, Inc. 
 * (C) Copyright 2001 Open Services Gateway Initiative, Inc. (the OSGi alliance)
 */

/* $Header: /cvshome/repository/ee/minimum/java/io/ObjectOutput.java,v 1.2 2003/03/12 03:17:39 hargrave Exp $ */

package java.io;
public abstract interface ObjectOutput extends java.io.DataOutput {
    public abstract void close() throws java.io.IOException;
    public abstract void flush() throws java.io.IOException;
    public abstract void write(byte[] var0) throws java.io.IOException;
    public abstract void write(byte[] var0, int var1, int var2) throws java.io.IOException;
    public abstract void write(int var0) throws java.io.IOException;
    public abstract void writeObject(java.lang.Object var0) throws java.io.IOException;
}

