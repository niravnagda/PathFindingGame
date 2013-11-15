/*
 * @(#)train_client.java   13/10/20
 * 
 * Copyright (c) 2005 your company name
 *
 * License agreement text here ...
 *
 *
 *
 */



package MiddleWare;

//~--- non-JDK imports --------------------------------------------------------

import Comm.*;

import org.omg.CORBA.*;
import org.omg.CosNaming.*;

public class train_client {
    static CommPackage commPack;
    static CommPackage trainRoute;

    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);

            // get the root naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

            // Use NamingContextExt instead of NamingContext. This is
            // part of the Interoperable naming Service.
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // resolve the Object Reference in Naming
            String name = "train_route";

            trainRoute = CommPackageHelper.narrow(ncRef.resolve_str(name));
            System.out.println("Obtained a handle on server object: " + trainRoute);
            System.out.println(trainRoute.get_route("mccallum", "bush_trnpke"));
            trainRoute.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
