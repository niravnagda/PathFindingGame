/*
 * @(#)bus_client.java   13/10/20
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

public class bus_client {
    private static String[] bus_host = { "-ORBInitialPort", "900", "-ORBInitialHost", "10.21.33.12" };
    static CommPackage      commPack;
    static CommPackage      busRoute;

    public String bus_route(String source, String destination) {
        try {
            ORB orb = ORB.init(bus_host, null);

            // get the root naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

            // Use NamingContextExt instead of NamingContext. This is
            // part of the Interoperable naming Service.
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // resolve the Object Reference in Naming
            String name = "bus_route";

            busRoute = CommPackageHelper.narrow(ncRef.resolve_str(name));
            System.out.println("Obtained a handle on server object: " + busRoute);

            String bus_result = busRoute.get_route(source, destination);

            busRoute.shutdown();

            return bus_result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
