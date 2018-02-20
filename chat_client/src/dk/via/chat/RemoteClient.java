package dk.via.chat;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class RemoteClient extends UnicastRemoteObject implements Client {
	private static final long serialVersionUID = 1L;

	private LinkedList<Message> messages;
	private Server server;
	private ClientHandle handle;
	private Observable observable;
	
	public RemoteClient(String name, Server server) throws RemoteException {
		this.server = server;
		this.messages = new LinkedList<>();
		this.handle = server.register(name, this);
		this.observable = new Observable();
	}
	
	public List<ClientHandle> getRecipients() throws RemoteException {
		List<ClientHandle> recipients = server.getClients();
		recipients.remove(this.handle);
		return recipients;
	}
	
	public void sendMessage(ClientHandle receiver, Message msg) throws RemoteException {
		Registry registry = server.getRegistry();
		Client recipient;
		try {
			recipient = (Client) registry.lookup(receiver.getId());
		} catch (NotBoundException e) {
			throw new RemoteException("Receiver unavailable", e);
		}
		recipient.receiveMessage(msg);
	}

	@Override
	public void receiveMessage(Message msg) {
		messages.add(msg);
		observable.notifyObservers();
	}

	public List<Message> getMessages() {
		return new LinkedList<Message>(messages);
	}
	
	public void addObserver(Observer o) {
		observable.addObserver(o);
	}

	public void deleteObserver(Observer o) {
		observable.deleteObserver(o);
	}

	@Override
	public String getId() throws RemoteException {
		return handle.getId();
	}

	@Override
	public String getName() throws RemoteException {
		return handle.getName();
	}
}
