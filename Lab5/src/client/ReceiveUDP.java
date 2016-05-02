package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveUDP extends Thread{
	DatagramSocket socket;
	
	public ReceiveUDP(DatagramSocket socket){
		this.socket = socket;
	}
	
	public void run(){
		while (true) {
			byte[] receiveBuffer = new byte[1024];
			DatagramPacket receive = new DatagramPacket(receiveBuffer, receiveBuffer.length);
			try {
				System.out.println("waiting to receive");
				socket.receive(receive);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String info = new String(receive.getData(), 0, receive.getLength());
			System.out.println("info:");
			System.out.println(info);
		}
	}
}
