import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements ServerIntf {

	// The class defines a default constructor ServerImpl() that throws a
	// RemoteException. This constructor is required because the superclass
	// constructor UnicastRemoteObject() also throws a RemoteException, and any
	// constructor in a remote object must declare that it throws RemoteException.
	public ServerImpl() throws RemoteException {

	}
	// The class extends the UnicastRemoteObject class, which is a built-in class in
	// Java RMI that provides functionality for exporting a remote object.

	public double add(double num1, double num2) throws RemoteException {
		return num1 + num2;
	}
}