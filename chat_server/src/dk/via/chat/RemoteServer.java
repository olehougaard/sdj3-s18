package dk.via.chat;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RemoteServer extends UnicastRemoteObject implements Server {
	private static final long serialVersionUID = 1L;

	private Map<String, ClientHandle> clients;
	private Registry registry;
	
	public RemoteServer(int registryPort) throws RemoteException {
		clients = new HashMap<>();
		registry = LocateRegistry.getRegistry(registryPort);
	}

	@Override
	public ClientHandle register(String name, Client client) throws RemoteException {
		int count = 1;
		while(clients.containsKey(name + count)) count++;
		String id = name + count;
		registry.rebind(id, client);
		ClientHandle clientHandle = new ClientHandle(id, name);
		clients.put(id, clientHandle);
		return clientHandle;
	}

	@Override
	public List<ClientHandle> getClients() throws RemoteException {
		return new ArrayList<>(clients.values());
	}
	
	@Override
	public Registry getRegistry() throws RemoteException {
		return registry;
	}
	
	public void register(String id) throws RemoteException {
		registry.rebind(id, this);
	}
}
