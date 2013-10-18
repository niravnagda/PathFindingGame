package train_route;
import Comm.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;


public class train_server {
	public static void main(String[] args){
		try{
			ORB orb = ORB.init(args, null);
			
			// get reference to rootpoa & activate the POAManager
		    POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
		    rootpoa.the_POAManager().activate();
		    
		    train_route trainRoute = new train_route();
		    trainRoute.setORB(orb);
		    
		      // get object reference from the servant
		      org.omg.CORBA.Object ref = rootpoa.servant_to_reference(trainRoute);
		      CommPackage href = CommPackageHelper.narrow(ref);
		      
		      // get the root naming context
		      // NameService invokes the name service
		      org.omg.CORBA.Object objRef =
		          orb.resolve_initial_references("NameService");
		      // Use NamingContextExt which is part of the InterOperable
		      // Naming Service (INS) specification.
		      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		      // bind the Object Reference in Naming
		      String name = "Train_Server";
		      NameComponent path[] = ncRef.to_name( name );
		      ncRef.rebind(path, href);
	
		      System.out.println("Server ready and waiting ...");
	
		      // wait for invocations from clients
		      orb.run();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}