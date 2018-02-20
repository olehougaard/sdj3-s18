package dk.via.chat;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.List;

public interface Server extends Remote {
	ClientHandle register(String name, Client client) throws RemoteException;
	List<ClientHandle> getClients() throws RemoteException;
	Registry getRegistry() throws RemoteException;
}
