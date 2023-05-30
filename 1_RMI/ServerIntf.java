import java.rmi.*;

//By defining this interface, you're specifying the remote methods that the server object will provide. The client can then invoke these methods remotely by obtaining a reference to the server object and calling the methods as if they were local.
//RMI is a mechanism in Java that allows objects residing in one JVM (Java Virtual Machine) to invoke methods on objects residing in another JVM, as if they were local objects.
// It extends the Remote interface, which is a marker interface indicating that objects implementing this interface can be accessed remotely through RMI.

public interface ServerIntf extends Remote {
	public double add(double num1, double num2) throws RemoteException;
}