/* (C) Copyright 2001 Sun Microsystems, Inc. 
 * (C) Copyright 2001 Open Services Gateway Initiative, Inc. (the OSGi alliance)
 */

/* $Header: /cvshome/repository/ee/minimum/java/io/ObjectInput.java,v 1.2 2003/03/12 03:17:39 hargrave Exp $ */

package java.io;
public abstract interface ObjectInput extends java.io.DataInput {
    public abstract int available() throws java.io.IOException;
    public abstract void close() throws java.io.IOException;
    public abstract int read() throws java.io.IOException;
    public abstract int read(byte[] var0) throws java.io.IOException;
    public abstract int read(byte[] var0, int var1, int var2) throws java.io.IOException;
    public abstract java.lang.Object readObject() throws java.lang.ClassNotFoundException, java.io.IOException;
    public abstract long skip(long var0) throws java.io.IOException;
}

