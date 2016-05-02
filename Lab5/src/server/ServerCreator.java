package server;

import java.util.Random;

public class ServerCreator {
	
	public static void main(String args[]){
		Random rand = new Random();
		
		int port = 39999 + rand.nextInt(100);
		TimeServerUDP timeServer = new TimeServerUDP(port);
		MulticastServer multiServer = new MulticastServer(port);
		multiServer.start();
		timeServer.start();
		
		
	}
}
