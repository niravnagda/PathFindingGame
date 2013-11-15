/*
 * @(#)CommPackageHolder.java   13/10/20
 * 
 * Copyright (c) 2005 your company name
 *
 * License agreement text here ...
 *
 *
 *
 */



package Comm;

/**
 * Holder class for : CommPackage
 *
 * @author OpenORB Compiler
 */
final public class CommPackageHolder implements org.omg.CORBA.portable.Streamable {

    /**
     * Internal CommPackage value
     */
    public Comm.CommPackage value;

    /**
     * Default constructor
     */
    public CommPackageHolder() {}

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public CommPackageHolder(Comm.CommPackage initial) {
        value = initial;
    }

    /**
     * Read CommPackage from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream) {
        value = CommPackageHelper.read(istream);
    }

    /**
     * Write CommPackage into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream) {
        CommPackageHelper.write(ostream, value);
    }

    /**
     * Return the CommPackage TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type() {
        return CommPackageHelper.type();
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
