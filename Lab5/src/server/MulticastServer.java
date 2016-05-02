package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastServer extends Thread{
	int port;
	static int multPort = 30000;
	
	public MulticastServer(int port){
		this.port = port;
	}
	
	public void run(){

		MulticastSocket server = null;
		
		
		
		try {
			server = new MulticastSocket(multPort);
			server.joinGroup(InetAddress.getByName("experiment.mcast.net"));
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while (true) {
			System.out.println("at least broadcast server is working");
			byte[] dataSize = new byte[1024];
			try {
				DatagramPacket receive = new DatagramPacket(dataSize, dataSize.length);
				server.receive(receive);
				String info = new String(receive.getData(), 0, receive.getLength());
				System.out.println("broadcast server received:");
				System.out.println(info);

				String result = "";
				result = checkCommand(info.toLowerCase());
				
				if (!result.equals("")) {
					System.out.println(result + " is sending");
					DatagramPacket send = new DatagramPacket(result.getBytes(), result.getBytes().length,
							receive.getAddress(), receive.getPort());
					server.send(send);
					System.out.println("is sent");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	private String checkCommand(String command){
		String result = "";
		switch (command) {
		case "broadcast":
			result = "this is server with port "+ port + ". I'm ready to receive message";
			break;

		}
		return result;
	}
}
