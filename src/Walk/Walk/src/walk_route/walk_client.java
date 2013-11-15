package walk_route;
import Comm.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

public class walk_client {
	static CommPackage commPack;
	static CommPackage walkRoute;
	
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
	        String name = "walk_route";
	        walkRoute = CommPackageHelper.narrow(ncRef.resolve_str(name));

	        System.out.println("Obtained a handle on server object: " + walkRoute);
	        System.out.println(walkRoute.get_route("mccallum","bush_trnpke"));
	        walkRoute.shutdown();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}

