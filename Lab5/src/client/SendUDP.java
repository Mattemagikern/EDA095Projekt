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

public class SendUDP extends Thread {
	DatagramSocket socket = null;
	MulticastSocket multiSocket = null;
	
	public SendUDP(DatagramSocket socket, MulticastSocket multiSocket){
		this.socket = socket;
		this.multiSocket = multiSocket;
		
	}
	
	public void run(){
		List<String> lista = new ArrayList<String>();
		Scanner input = new Scanner(System.in);
		
		try {
			multiSocket.joinGroup(InetAddress.getByName("experiment.mcast.net"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (true){
			String stringInput = input.nextLine();
			lista = parser(stringInput);
			byte[] sendBuffer = lista.get(4).getBytes();
			InetAddress fish = null;
			if(lista.get(4).equals("broadcast")){
				DatagramPacket send = null;
				try {
					send = new DatagramPacket(sendBuffer, sendBuffer.length, InetAddress.getByName("experiment.mcast.net"), Integer.parseInt(lista.get(3)));
					multiSocket.send(send);
					System.out.println("sent broadcast");
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
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
					System.out.println("sent normal package");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	

	private static List<String> parser(String text){
		List<String> lista = new ArrayList<String>();
		int index = text.indexOf(" ");
		lista.add(text.substring(0, index));
		text = text.substring(index+1);
		
		index = text.indexOf(" ");
		lista.add(text.substring(0,index));
		text = text.substring(index+1);
		
		index = text.indexOf(" ");
		lista.add(text.substring(0,index));
		text = text.substring(index+1);
		
		index = text.indexOf(" ");
		lista.add(text.substring(0,index));
		text = text.substring(index+1);
		
		lista.add(text);
		
		return lista;
		
	}
}
