/* (C) Copyright 2001 Sun Microsystems, Inc. 
 * (C) Copyright 2001 Open Services Gateway Initiative, Inc. (the OSGi alliance)
 */

/* $Header: /cvshome/repository/ee/minimum/java/io/ObjectOutputStream.java,v 1.2 2003/03/12 03:17:39 hargrave Exp $ */

package java.io;
public class ObjectOutputStream extends java.io.OutputStream implements java.io.ObjectOutput, java.io.ObjectStreamConstants {
    protected ObjectOutputStream() throws java.io.IOException, java.lang.SecurityException { }
    public ObjectOutputStream(java.io.OutputStream var0) throws java.io.IOException { }
    protected void annotateClass(java.lang.Class var0) throws java.io.IOException { }
    protected void annotateProxyClass(java.lang.Class var0) throws java.io.IOException { }
    public void close() throws java.io.IOException { }
    public void defaultWriteObject() throws java.io.IOException { }
    protected void drain() throws java.io.IOException { }
    protected boolean enableReplaceObject(boolean var0) throws java.lang.SecurityException { return false; }
    public void flush() throws java.io.IOException { }
    public java.io.ObjectOutputStream.PutField putFields() throws java.io.IOException { return null; }
    protected java.lang.Object replaceObject(java.lang.Object var0) throws java.io.IOException { return null; }
    public void reset() throws java.io.IOException { }
    public void useProtocolVersion(int var0) throws java.io.IOException { }
    public void write(byte[] var0) throws java.io.IOException { }
    public void write(byte[] var0, int var1, int var2) throws java.io.IOException { }
    public void write(int var0) throws java.io.IOException { }
    public void writeBoolean(boolean var0) throws java.io.IOException { }
    public void writeByte(int var0) throws java.io.IOException { }
    public void writeBytes(java.lang.String var0) throws java.io.IOException { }
    public void writeChar(int var0) throws java.io.IOException { }
    public void writeChars(java.lang.String var0) throws java.io.IOException { }
    public void writeDouble(double var0) throws java.io.IOException { }
    public void writeFields() throws java.io.IOException { }
    public void writeFloat(float var0) throws java.io.IOException { }
    public void writeInt(int var0) throws java.io.IOException { }
    public void writeLong(long var0) throws java.io.IOException { }
    protected void writeClassDescriptor(java.io.ObjectStreamClass var0) throws java.io.IOException { }
    public final void writeObject(java.lang.Object var0) throws java.io.IOException { }
    protected void writeObjectOverride(java.lang.Object var0) throws java.io.IOException { }
    public void writeShort(int var0) throws java.io.IOException { }
    protected void writeStreamHeader() throws java.io.IOException { }
    public void writeUTF(java.lang.String var0) throws java.io.IOException { }
    public static abstract class PutField {
        public PutField() { }
        public abstract void put(java.lang.String var0, boolean var1);
        public abstract void put(java.lang.String var0, char var1);
        public abstract void put(java.lang.String var0, byte var1);
        public abstract void put(java.lang.String var0, short var1);
        public abstract void put(java.lang.String var0, int var1);
        public abstract void put(java.lang.String var0, long var1);
        public abstract void put(java.lang.String var0, float var1);
        public abstract void put(java.lang.String var0, double var1);
        public abstract void put(java.lang.String var0, java.lang.Object var1);
        public abstract void write(java.io.ObjectOutput var0) throws java.io.IOException;
    }
}

