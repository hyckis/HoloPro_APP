/* (C) Copyright 2001 Sun Microsystems, Inc. 
 * (C) Copyright 2001 Open Services Gateway Initiative, Inc. (the OSGi alliance)
 */

/* $Header: /cvshome/repository/ee/minimum/java/io/Externalizable.java,v 1.2 2003/03/12 03:17:39 hargrave Exp $ */

package java.io;
public abstract interface Externalizable extends java.io.Serializable {
    public abstract void readExternal(java.io.ObjectInput var0) throws java.io.IOException, java.lang.ClassNotFoundException;
    public abstract void writeExternal(java.io.ObjectOutput var0) throws java.io.IOException;
}

