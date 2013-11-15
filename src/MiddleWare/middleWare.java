/*
 * @(#)middleWare.java   13/10/20
 *
 * Copyright (c) 2005 your company name
 *
 * License agreement text here ...
 *
 *
 *
 */



package MiddleWare;

//~--- JDK imports ------------------------------------------------------------

import java.util.HashMap;

public class middleWare {
    public HashMap<String, DataPojo> getData() {
        HashMap<String, DataPojo> data      = new HashMap<String, DataPojo>();
        DataPojo                  trainData = new DataPojo();
        DataPojo                  busData   = new DataPojo();
        DataPojo                  walkData  = new DataPojo();
        DataPojo                  carData   = new DataPojo();
        DataPojo				  No_Trans = new DataPojo(); 
        // Call the train_server and get the path for train_route
        train_client tc           = new train_client();
        String       train_result = tc.train_route("mccallum", "bush_trnpke");
        String[]     train        = train_result.split("\\s");

        
        trainData.setSource(train[0]);
        trainData.setDestination(train[1]);
        trainData.setTime(Integer.parseInt(train[2]));
        trainData.setCost(Integer.parseInt(train[3]));

        //Call bus server and get the path for bus route
		bus_client bc         = new bus_client();
        String     bus_result = bc.bus_route("mccallum", "bush_trnpke");
        System.out.println(bus_result);
        String[]   bus        = bus_result.split("\\s");

        busData.setSource(bus[0]);
        busData.setDestination(bus[1]);
        busData.setTime(Integer.parseInt(bus[2]));
        busData.setCost(Integer.parseInt(bus[3]));
        
        // Call the walk server and get the path for walk_route
        walk_client wc          = new walk_client();
        String      walk_result = wc.walk_route("mccallum", "bush_trnpke");
        String[]    walk        = walk_result.split("\\s");

        walkData.setSource(walk[0]);
        walkData.setDestination(walk[1]);
        walkData.setTime(Integer.parseInt(walk[2]));
        walkData.setCost(Integer.parseInt(walk[3]));
        
        car_client cc         = new car_client();
        String     car_result = cc.car_route("mccallum", "bush_trnpke");
        String[]   car        = car_result.split("\\s");

        carData.setSource(car[0]);
        carData.setDestination(car[1]);
        carData.setTime(Integer.parseInt(car[2]));
        carData.setCost(Integer.parseInt(car[3]));
        data.put("train", trainData);
        data.put("bus", busData);
        data.put("walk", walkData);
        data.put("car", carData);

        
        No_Trans.setSource(train[0]);
        No_Trans.setSource(train[1]);
        No_Trans.setCost(5);
        No_Trans.setTime(999);
        data.put("No_Trans", No_Trans);
        
        return data;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
