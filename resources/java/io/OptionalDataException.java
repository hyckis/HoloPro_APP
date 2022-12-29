/* (C) Copyright 2001 Sun Microsystems, Inc. 
 * (C) Copyright 2001 Open Services Gateway Initiative, Inc. (the OSGi alliance)
 */

/* $Header: /cvshome/repository/ee/minimum/java/io/OptionalDataException.java,v 1.2 2003/03/12 03:17:39 hargrave Exp $ */

package java.io;
public class OptionalDataException extends java.io.ObjectStreamException {
    OptionalDataException() { }
    OptionalDataException(java.lang.String var0) { }
    public boolean eof;
    public int length;
}

