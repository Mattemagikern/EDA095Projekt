package client;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.MulticastSocket;

public class ClientCreator {
	
	public static void main(String args[]) {
		MulticastSocket multiSocket = null;
		DatagramSocket socket = null;
		try {
			multiSocket = new MulticastSocket();
			socket = new DatagramSocket();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SendUDP sender = new SendUDP(socket, multiSocket);
		MCreceiver mcReceiver = new MCreceiver(multiSocket);
		ReceiveUDP receiver = new ReceiveUDP(socket);

		System.out.println("da fack");
		sender.start();
		System.out.println("SendUDP started");
		mcReceiver.start();
		System.out.println("mcReciver started");
		receiver.start();
		System.out.println("receiver started");
	}

}
