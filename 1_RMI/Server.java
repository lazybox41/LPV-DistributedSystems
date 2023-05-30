import java.rmi.*;

public class Server {

	// The purpose of this Server class is to create an instance of the ServerImpl
	// class and bind it to a name in the RMI registry. By doing so, the server
	// object becomes accessible to clients who want to invoke its remote methods.

	// Note that in order for this code to work, you need to have an RMI registry
	// running, which serves as a central repository for storing references to
	// remote objects. The RMI registry should be started before running this server
	// application.

	// Once the server is running and the remote object is bound to a name in the
	// RMI registry, clients can perform a lookup on the RMI registry to obtain a
	// reference to the server object using the same name. Then, they can invoke the
	// remote methods defined in the ServerIntf interface on the obtained server
	// object reference.
	public static void main(String[] args) {
		try {
			ServerImpl si = new ServerImpl();
			Naming.bind("Server", si);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}