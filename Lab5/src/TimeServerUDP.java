import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TimeServerUDP {

	public static void main(String[] args) {
		TimeServerUDP server = new TimeServerUDP();
		server.start();
		
		

	}
	
	private void start(){
		byte[] dataSize = new byte[1024];
		
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket(30000);
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		while (true){
			
			try {
				DatagramPacket receive = new DatagramPacket(dataSize, dataSize.length);
				socket.receive(receive);
				String info = new String(receive.getData(), 0, receive.getLength());
				
				String result = "";
				result = checkCommand(info.toLowerCase());
					
				DatagramPacket send = new DatagramPacket(result.getBytes(), result.getBytes().length, receive.getAddress(), receive.getPort());
				socket.send(send);
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
