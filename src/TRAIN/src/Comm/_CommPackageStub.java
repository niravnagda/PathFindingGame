/*
 * @(#)_CommPackageStub.java   13/10/20
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
public class _CommPackageStub extends org.omg.CORBA.portable.ObjectImpl implements CommPackage {
    static final String[]      _ids_list = { "IDL:Comm/CommPackage:1.0" };
    private final static Class _opsClass = Comm.CommPackageOperations.class;

    public String[] _ids() {
        return _ids_list;
    }

    /**
     * Operation get_route
     */
    public String get_route(String source, String destination) {
        while (true) {
            if (!this._is_local()) {
                org.omg.CORBA.portable.InputStream _input = null;

                try {
                    org.omg.CORBA.portable.OutputStream _output = this._request("get_route", true);

                    _output.write_string(source);
                    _output.write_string(destination);
                    _input = this._invoke(_output);

                    String _arg_ret = _input.read_string();

                    return _arg_ret;
                } catch (org.omg.CORBA.portable.RemarshalException _exception) {
                    continue;
                } catch (org.omg.CORBA.portable.ApplicationException _exception) {
                    String _exception_id = _exception.getId();

                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: " + _exception_id);
                } finally {
                    this._releaseReply(_input);
                }
            } else {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("get_route", _opsClass);

                if (_so == null) {
                    continue;
                }

                Comm.CommPackageOperations _self = (Comm.CommPackageOperations) _so.servant;

                try {
                    return _self.get_route(source, destination);
                } finally {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation shutdown
     */
    public void shutdown() {
        while (true) {
            if (!this._is_local()) {
                org.omg.CORBA.portable.InputStream _input = null;

                try {
                    org.omg.CORBA.portable.OutputStream _output = this._request("shutdown", false);

                    _input = this._invoke(_output);

                    return;
                } catch (org.omg.CORBA.portable.RemarshalException _exception) {
                    continue;
                } catch (org.omg.CORBA.portable.ApplicationException _exception) {
                    String _exception_id = _exception.getId();

                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: " + _exception_id);
                } finally {
                    this._releaseReply(_input);
                }
            } else {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("shutdown", _opsClass);

                if (_so == null) {
                    continue;
                }

                Comm.CommPackageOperations _self = (Comm.CommPackageOperations) _so.servant;

                try {
                    _self.shutdown();

                    return;
                } finally {
                    _servant_postinvoke(_so);
                }
            }
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
