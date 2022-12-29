/* (C) Copyright 2001 Sun Microsystems, Inc. 
 * (C) Copyright 2001 Open Services Gateway Initiative, Inc. (the OSGi alliance)
 */

/* $Header: /cvshome/repository/ee/minimum/java/io/File.java,v 1.2 2003/03/12 03:17:39 hargrave Exp $ */

package java.io;
public class File implements java.io.Serializable, java.lang.Comparable {
    public File(java.io.File var0, java.lang.String var1) { }
    public File(java.lang.String var0) { }
    public File(java.lang.String var0, java.lang.String var1) { }
    public boolean canRead() { return false; }
    public boolean canWrite() { return false; }
    public int compareTo(java.lang.Object var0) { return 0; }
    public int compareTo(java.io.File var0) { return 0; }
    public boolean delete() { return false; }
    public void deleteOnExit() { }
    public boolean equals(java.lang.Object var0) { return false; }
    public boolean exists() { return false; }
    public java.lang.String getAbsolutePath() { return null; }
    public java.lang.String getCanonicalPath() throws java.io.IOException { return null; }
    public java.lang.String getName() { return null; }
    public java.lang.String getParent() { return null; }
    public java.lang.String getPath() { return null; }
    public int hashCode() { return 0; }
    public boolean isAbsolute() { return false; }
    public boolean isDirectory() { return false; }
    public boolean isFile() { return false; }
    public long lastModified() { return 0l; }
    public long length() { return 0l; }
    public java.lang.String[] list() { return null; }
    public boolean mkdir() { return false; }
    public boolean mkdirs() { return false; }
    public boolean createNewFile() throws java.io.IOException { return false; }
    public static java.io.File createTempFile(java.lang.String var0, java.lang.String var1, java.io.File var2) throws java.io.IOException { return null; }
    public boolean renameTo(java.io.File var0) { return false; }
    public java.lang.String toString() { return null; }
    public java.net.URL toURL() throws java.net.MalformedURLException { return null; }
    public final static char separatorChar; static { separatorChar = 0; }
    public final static java.lang.String separator; static { separator = null; }
    public final static char pathSeparatorChar; static { pathSeparatorChar = 0; }
    public final static java.lang.String pathSeparator; static { pathSeparator = null; }
}

