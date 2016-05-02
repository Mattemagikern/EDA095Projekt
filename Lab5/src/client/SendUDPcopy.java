package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SendUDPcopy {

	public static void main(String args[]) {
		while (true) {
			List<String> lista = new ArrayList<String>();
			Scanner input = new Scanner(System.in);
			DatagramSocket socket = null;
			try {
				socket = new DatagramSocket();
			} catch (SocketException e1) {
				e1.printStackTrace();
			}
			String stringInput = input.nextLine();
			lista = parser(stringInput);
			if (lista.size() == 5) {
				byte[] sendBuffer = lista.get(4).getBytes();
				InetAddress fish = null;
				try {
					fish = InetAddress.getByName(lista.get(2));
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				DatagramPacket send = new DatagramPacket(sendBuffer, sendBuffer.length, fish,
						Integer.parseInt(lista.get(3)));
				try {
					socket.send(send);
				} catch (IOException e) {
					e.printStackTrace();
				}
				byte[] receiveBuffer = new byte[1024];
				DatagramPacket receive = new DatagramPacket(receiveBuffer, receiveBuffer.length);
				try {
					socket.receive(receive);
				} catch (IOException e) {
					e.printStackTrace();
				}
				String info = new String(receive.getData(), 0, receive.getLength());
				System.out.println(info);
			} else if (lista.size() == 4){
				
				byte[] sendBuffer = lista.get(3).getBytes();
				MulticastSocket multiSocket;
				InetAddress fish = null;
				
				try {
					multiSocket = new MulticastSocket(Integer.parseInt(lista.get(2)));
					fish = InetAddress.getByName("231.123.123.123");
					multiSocket.joinGroup(fish);
					DatagramPacket send = new DatagramPacket(sendBuffer, sendBuffer.length, fish,
							Integer.parseInt(lista.get(2)));
					multiSocket.send(send);
					System.out.println("client sent message");
					System.out.println(lista.get(3));
					
					byte[] receiveBuffer = new byte[1024];
					DatagramPacket receive = new DatagramPacket(receiveBuffer, receiveBuffer.length);
					try {
						socket.receive(receive);
					} catch (IOException e) {
						e.printStackTrace();
					}
					String info = new String(receive.getData(), 0, receive.getLength());
					
					System.out.println(info);
					multiSocket.leaveGroup(fish);
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
			}
		}
	}
	
	private static List<String> parser(String text){
		List<String> lista = new ArrayList<String>();
		String[] strings = text.split(" ");
		for (int i = 0; i < strings.length;i++){
			int index = text.indexOf(" ");
			if(text.contains(" ")){
				lista.add(text.substring(0, index));
				text = text.substring(index+1);
			} else {
				lista.add(text);
			}
		}
	
		return lista;
		
	}
}
