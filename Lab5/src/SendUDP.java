import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SendUDP {
	
	public static void main(String args[]){
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
		byte[] sendBuffer = lista.get(4).getBytes();
		InetAddress fish = null;
		try {
			fish = InetAddress.getByName(lista.get(2));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DatagramPacket send = new DatagramPacket(sendBuffer, sendBuffer.length, fish, Integer.parseInt(lista.get(3)));
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
		
		for (int i = 0; i < lista.size();i++){
			System.out.println(lista.get(i));
		}
		return lista;
		
	}
}
