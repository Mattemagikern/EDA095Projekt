package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

public class MCreceiver extends Thread {
	MulticastSocket socket;
	public MCreceiver(MulticastSocket socket){
		this.socket = socket;
	}
	
	public void run(){
		while (true){
			byte[] receiveBuffer = new byte[1024];
			DatagramPacket receive = new DatagramPacket(receiveBuffer, receiveBuffer.length);
			try {
				socket.receive(receive);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String info = new String(receive.getData(), 0, receive.getLength());
			System.out.println(info);
		}
	}
}
