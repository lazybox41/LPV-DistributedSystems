import java.rmi.*;

//It is responsible for performing a lookup on the RMI registry to obtain a reference to the remote server object and invoking its remote method.

public class Client {
	public static void main(String[] args) {
		try {
			String serverURL = "rmi://" + args[0] + "/Server";
			ServerIntf sIntf = (ServerIntf) Naming.lookup(serverURL);
			double d1 = Double.valueOf(args[1]).doubleValue();
			double d2 = Double.valueOf(args[2]).doubleValue();
			System.out.println("Sum is" + sIntf.add(d1, d2));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}
