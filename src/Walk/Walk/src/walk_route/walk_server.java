package walk_route;
import Comm.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;


public class walk_server {
	public static void main(String[] args){
		while(true){
			try{
				ORB orb = ORB.init(args, null);

				// get reference to rootpoa & activate the POAManager
				POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
				rootpoa.the_POAManager().activate();

				walk_route walkRoute = new walk_route();
				walkRoute.setORB(orb);

				// get object reference from the servant
				org.omg.CORBA.Object ref = rootpoa.servant_to_reference(walkRoute);
				CommPackage href = CommPackageHelper.narrow(ref);

				// get the root naming context
				// NameService invokes the name service
				org.omg.CORBA.Object objRef =
						orb.resolve_initial_references("NameService");
				// Use NamingContextExt which is part of the Interoperable
				// Naming Service (INS) specification.
				NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
				// bind the Object Reference in Naming
				String name = "walk_route";
				NameComponent path[] = ncRef.to_name( name );
				ncRef.rebind(path, href);

				System.out.println("Server ready and waiting ...");

				// wait for invocations from clients
				orb.run();
			} catch(Exception e){
				e.printStackTrace();
			}
		}  
		//System.out.println("Server exiting ...");
	}
}
