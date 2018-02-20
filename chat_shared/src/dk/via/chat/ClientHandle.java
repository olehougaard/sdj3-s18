package dk.via.chat;

import java.io.Serializable;

public class ClientHandle implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	
	public ClientHandle(String id, String name) {
		if (id == null || name == null) throw new NullPointerException();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public int hashCode() {
		return id.hashCode() ^ name.hashCode();
	}
	
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj.getClass() != getClass()) return false;
		ClientHandle other = (ClientHandle) obj;
		return id.equals(other.id) && name.equals(other.name);
	}
}
