package server;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TimeServerUDP extends Thread{
	DatagramSocket socket = null;
	int port;
	
	public TimeServerUDP(int port){
		this.port = port;
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
	}
	
	public void run(){
		byte[] dataSize = new byte[1024];
		
		
		
		while (true){
			
			try {
				DatagramPacket receive = new DatagramPacket(dataSize, dataSize.length);
				socket.receive(receive);
				String info = new String(receive.getData(), 0, receive.getLength());
				System.out.println("normal server received:");
				System.out.println(info);
				
				String result = "";
				result = checkCommand(info.toLowerCase());
				if (result != "null"){
					DatagramPacket send = new DatagramPacket(result.getBytes(), result.getBytes().length, receive.getAddress(), receive.getPort());
					socket.send(send);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String checkCommand(String command){
		String result = "";
		switch (command) {
		case "gettime":
			result = getTime();
			break;
		case "getdate":
			result = getDate();
			break;
		case "broadcast":
			result = "null";
			break;

		}
		if (result == ""){
			return "no valid command has been sent in";
		}
		return result;
	}
	
	private String getTime(){
		DisplayDate date = new DisplayDate();
		return date.getLocalTime();
	}
	
	private String getDate(){
		DisplayDate date = new DisplayDate();
		return date.getLocalDate();
	}

}
