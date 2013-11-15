package train_route;
import Comm.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

public class train_client {
	static CommPackage commPack;
	static CommPackage trainRoute;
	
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
	        String name = "train_route";
	        trainRoute = CommPackageHelper.narrow(ncRef.resolve_str(name));

	        System.out.println("Obtained a handle on server object: " + trainRoute);
	        System.out.println(trainRoute.get_route("mccallum","bush_trnpke"));
	        trainRoute.shutdown();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}

