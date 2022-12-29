/* (C) Copyright 2001 Sun Microsystems, Inc. 
 * (C) Copyright 2001 Open Services Gateway Initiative, Inc. (the OSGi alliance)
 */

/* $Header: /cvshome/repository/ee/minimum/java/io/DataOutput.java,v 1.2 2003/03/12 03:17:39 hargrave Exp $ */

package java.io;
public abstract interface DataOutput {
    public abstract void write(byte[] var0) throws java.io.IOException;
    public abstract void write(byte[] var0, int var1, int var2) throws java.io.IOException;
    public abstract void write(int var0) throws java.io.IOException;
    public abstract void writeBoolean(boolean var0) throws java.io.IOException;
    public abstract void writeByte(int var0) throws java.io.IOException;
    public abstract void writeBytes(java.lang.String var0) throws java.io.IOException;
    public abstract void writeChar(int var0) throws java.io.IOException;
    public abstract void writeChars(java.lang.String var0) throws java.io.IOException;
    public abstract void writeDouble(double var0) throws java.io.IOException;
    public abstract void writeFloat(float var0) throws java.io.IOException;
    public abstract void writeInt(int var0) throws java.io.IOException;
    public abstract void writeLong(long var0) throws java.io.IOException;
    public abstract void writeShort(int var0) throws java.io.IOException;
    public abstract void writeUTF(java.lang.String var0) throws java.io.IOException;
}

