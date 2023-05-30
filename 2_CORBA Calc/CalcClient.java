import CalcModule.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CosNaming.NamingContextExtHelper;
import java.io.*;

public class CalcClient {
	public static void main(String[] args) {
		Calc CalcImpl = null;
		try {
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			String name = "Calc";
			CalcImpl = CalcHelper.narrow(ncRef.resolve_str(name));
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			float num1;
			System.out.println("Enter first num ");
			num1 = Float.parseFloat(br.readLine());
			float num2;
			System.out.println("Enter second num ");
			num2 = Float.parseFloat(br.readLine());
			float res = CalcImpl.add(num1, num2);
			System.out.println("Sum is " + res);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}