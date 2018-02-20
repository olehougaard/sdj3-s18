package dk.via.chat;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Client extends Remote {
	void receiveMessage(Message msg) throws RemoteException;
	String getId() throws RemoteException;
	String getName() throws RemoteException;
}
