/*
 * @(#)CommPackageHelper.java   13/10/20
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
 * Helper class for : CommPackage
 *
 * @author OpenORB Compiler
 */
public class CommPackageHelper {

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;
    private final static String           _id = "IDL:Comm/CommPackage:1.0";

    /**
     * Insert CommPackage into an any
     * @param a an any
     * @param t CommPackage value
     */
    public static void insert(org.omg.CORBA.Any a, Comm.CommPackage t) {
        a.insert_Object(t, type());
    }

    /**
     * Extract CommPackage from an any
     *
     * @param a an any
     * @return the extracted CommPackage value
     */
    public static Comm.CommPackage extract(org.omg.CORBA.Any a) {
        if (!a.type().equivalent(type())) {
            throw new org.omg.CORBA.MARSHAL();
        }

        try {
            return Comm.CommPackageHelper.narrow(a.extract_Object());
        } catch (final org.omg.CORBA.BAD_PARAM e) {
            throw new org.omg.CORBA.MARSHAL(e.getMessage());
        }
    }

    /**
     * Return the CommPackage TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type() {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();

            _tc = orb.create_interface_tc(id(), "CommPackage");
        }

        return _tc;
    }

    /**
     * Return the CommPackage IDL ID
     * @return an ID
     */
    public static String id() {
        return _id;
    }

    /**
     * Read CommPackage from a marshalled stream
     * @param istream the input stream
     * @return the readed CommPackage value
     */
    public static Comm.CommPackage read(org.omg.CORBA.portable.InputStream istream) {
        return (Comm.CommPackage) istream.read_Object(Comm._CommPackageStub.class);
    }

    /**
     * Write CommPackage into a marshalled stream
     * @param ostream the output stream
     * @param value CommPackage value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, Comm.CommPackage value) {
        ostream.write_Object((org.omg.CORBA.portable.ObjectImpl) value);
    }

    /**
     * Narrow CORBA::Object to CommPackage
     * @param obj the CORBA Object
     * @return CommPackage Object
     */
    public static CommPackage narrow(org.omg.CORBA.Object obj) {
        if (obj == null) {
            return null;
        }

        if (obj instanceof CommPackage) {
            return (CommPackage) obj;
        }

        if (obj._is_a(id())) {
            _CommPackageStub stub = new _CommPackageStub();

            stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate());

            return stub;
        }

        throw new org.omg.CORBA.BAD_PARAM();
    }

    /**
     * Unchecked Narrow CORBA::Object to CommPackage
     * @param obj the CORBA Object
     * @return CommPackage Object
     */
    public static CommPackage unchecked_narrow(org.omg.CORBA.Object obj) {
        if (obj == null) {
            return null;
        }

        if (obj instanceof CommPackage) {
            return (CommPackage) obj;
        }

        _CommPackageStub stub = new _CommPackageStub();

        stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate());

        return stub;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
