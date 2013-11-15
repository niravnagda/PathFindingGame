/*
 * @(#)CommPackagePOA.java   13/10/20
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
 * Interface definition: CommPackage.
 *
 * @author OpenORB Compiler
 */
public abstract class CommPackagePOA extends org.omg.PortableServer.Servant
        implements CommPackageOperations, org.omg.CORBA.portable.InvokeHandler {
    private static String[] _ids_list = { "IDL:Comm/CommPackage:1.0" };

    public CommPackage _this() {
        return CommPackageHelper.narrow(_this_object());
    }

    public CommPackage _this(org.omg.CORBA.ORB orb) {
        return CommPackageHelper.narrow(_this_object(orb));
    }

    public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte[] objectId) {
        return _ids_list;
    }

    public final org.omg.CORBA.portable.OutputStream _invoke(final String opName,
            final org.omg.CORBA.portable.InputStream _is, final org.omg.CORBA.portable.ResponseHandler handler) {
        if (opName.equals("get_route")) {
            return _invoke_get_route(_is, handler);
        } else if (opName.equals("shutdown")) {
            return _invoke_shutdown(_is, handler);
        } else {
            throw new org.omg.CORBA.BAD_OPERATION(opName);
        }
    }

    // helper methods
    private org.omg.CORBA.portable.OutputStream _invoke_get_route(final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String                              arg0_in     = _is.read_string();
        String                              arg1_in     = _is.read_string();
        String                              _arg_result = get_route(arg0_in, arg1_in);

        _output = handler.createReply();
        _output.write_string(_arg_result);

        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_shutdown(final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;

        shutdown();
        _output = handler.createReply();

        return _output;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
