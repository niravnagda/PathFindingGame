/*
 * @(#)car_client.java   13/10/20
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

public class car_client {
    private static String[] car_host = { "-ORBInitialPort", "900", "-ORBInitialHost", "10.176.64.249" };
    static CommPackage      commPack;
    static CommPackage      carRoute;

    public String car_route(String source, String destination) {
        try {
            ORB orb = ORB.init(car_host, null);

            // get the root naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

            // Use NamingContextExt instead of NamingContext. This is
            // part of the Interoperable naming Service.
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // resolve the Object Reference in Naming
            String name = "car_route";

            carRoute = CommPackageHelper.narrow(ncRef.resolve_str(name));
            System.out.println("Obtained a handle on server object: " + carRoute);

            String car_result = carRoute.get_route(source, destination);

            carRoute.shutdown();

            return car_result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
