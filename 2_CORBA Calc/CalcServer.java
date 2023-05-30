import org.omg.CORBA.*;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.*;
import java.util.*;
import CalcModule.Calc;

public class CalcServer {
	public static void main(String[] args) {
		try {
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
			POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootPOA.the_POAManager().activate();
			CalcImpl calc = new CalcImpl();
			org.omg.CORBA.Object ref = rootPOA.servant_to_reference(calc);
			Calc href = CalcModule.CalcHelper.narrow(ref);
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			org.omg.CosNaming.NamingContextExt ncRef = org.omg.CosNaming.NamingContextExtHelper.narrow(objRef);
			String name = "Calc";
			org.omg.CosNaming.NameComponent path[] = ncRef.to_name(name);
			ncRef.rebind(path, href);
			orb.run();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
