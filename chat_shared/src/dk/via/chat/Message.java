package dk.via.chat;

import java.io.Serializable;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String text;
	private ClientHandle sender;

	public Message(String text, ClientHandle sender) {
		if (text == null || sender == null) throw new NullPointerException();
		this.text = text;
		this.sender = sender;
	}

	public String getText() {
		return text;
	}

	public ClientHandle getSender() {
		return sender;
	}

	@Override
	public int hashCode() {
		return text.hashCode() ^ sender.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		return text.equals(other.text) && sender.equals(other.sender);
	}
	
}
