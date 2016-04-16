package mainprogramserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;



public class ServerCreator {

	public void startServer(){
		Mailbox box = new Mailbox();
		//Postman man = new Postman(box);
		ArrayList<Participant> participants = new ArrayList<Participant>();
		ServerWrite serverWrite = new ServerWrite(box, participants);
		serverWrite.start();
		ServerSocket server;
		try {
			server = new ServerSocket(40000);
			boolean loop = true;
			while(loop){
				Socket connection = server.accept();
				System.out.println(connection.getInetAddress().toString() + " has connected");
				Participant participant = new Participant(connection);
				participants.add(participant);
				ServerRead serverRead = new ServerRead(box, participant);
				serverRead.start();

				
			
			}
			server.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		ServerCreator creator = new ServerCreator();
		creator.startServer();

	}

}
