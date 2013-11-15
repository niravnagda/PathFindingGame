/*
 * @(#)walk_client.java   13/10/20
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

public class walk_client {
    private static String[] walk_host = { "-ORBInitialPort", "900", "-ORBInitialHost", "10.176.64.39" };
    static CommPackage      commPack;
    static CommPackage      walkRoute;

    public String walk_route(String source, String destination) {
        try {
            ORB orb = ORB.init(walk_host, null);

            // get the root naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

            // Use NamingContextExt instead of NamingContext. This is
            // part of the Interoperable naming Service.
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // resolve the Object Reference in Naming
            String name = "walk_route";

            walkRoute = CommPackageHelper.narrow(ncRef.resolve_str(name));
            System.out.println("Obtained a handle on server object: " + walkRoute);

            String walk_result = walkRoute.get_route(source, destination);

            walkRoute.shutdown();

            return walk_result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
