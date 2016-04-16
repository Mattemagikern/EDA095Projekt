package mainprogramserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerRead extends Thread{
	
	Mailbox box;
	Participant participant;
	Socket socket;
	String name;
	
	public ServerRead(Mailbox box, Participant participant ){
		this.box = box;
		this.participant = participant;
		this.socket = participant.getSocket();
		this.name = participant.getSocket().getInetAddress().toString();
	}
	
	public void run(){
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line;
			while (!socket.isClosed()){
				line = reader.readLine();
				//System.out.println("the line read from the client: " + line);
				box.setText(name+": "+line);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
