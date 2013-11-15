package car_route;
import Comm.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

public class car_client {
	static CommPackage commPack;
	static CommPackage busRoute;
	
	public static void main(String[] args){
		try{
			ORB orb = ORB.init(args,null);
			
	        // get the root naming context
	        org.omg.CORBA.Object objRef = 
	            orb.resolve_initial_references("NameService");
	        // Use NamingContextExt instead of NamingContext. This is 
	        // part of the Interoperable naming Service.  
	        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
	        
	     // resolve the Object Reference in Naming
	        String name = "car_route";
	        busRoute = CommPackageHelper.narrow(ncRef.resolve_str(name));

	        System.out.println("Obtained a handle on server object: " + busRoute);
	        System.out.println(busRoute.get_route("mccallum","bush_trnpke"));
	        busRoute.shutdown();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}

