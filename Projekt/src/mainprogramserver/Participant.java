package mainprogramserver;

import java.net.Socket;

public class Participant {
	Socket socket;
	String name;
	public Participant(Socket socket){
		this.socket = socket;
		this.name = socket.getInetAddress().getHostName();
	}
	
	public Socket getSocket(){
		return socket;
	}
	
	public String getName(){
		return name;
	}
}
