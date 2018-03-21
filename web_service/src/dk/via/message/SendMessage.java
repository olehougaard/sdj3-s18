package dk.via.message;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class SendMessage {
	public SendMessage() {
		
	}
	
	@WebMethod
	public void sendMessage(Message message) {
		//...
	}
	
	@WebMethod
	public Message[] getNewMessages() {
		return new Message[0];
	}
}
