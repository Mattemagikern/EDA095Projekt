package Lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ServerThreads extends Thread{
	Socket socket;
	public ServerThreads(Socket socket){
		this.socket = socket;
	}
	
	public void run() {
		
		try {
			InputStream input = socket.getInputStream();
			OutputStream output = socket.getOutputStream();
			InetAddress clientAddress = socket.getInetAddress();
			System.out.println(clientAddress.getHostAddress());
			
			StringBuilder builder = new StringBuilder();
			InputStreamReader reader = new InputStreamReader(input); //converts bytes into readable chars
			BufferedReader buffReader = new BufferedReader(reader);
			String character;
			while ((character = buffReader.readLine()) != null){ //reads next character and checks so we're not at the end
				System.out.println(character);
				String test = "fish";
				output.write(test.getBytes());
				output.flush();
			}
			System.out.println(builder.toString());
			
			String test = "fish";
			output.write(test.getBytes());
			output.flush();
			socket.close();
			
		} catch (IOException e){
			
		}
	}

}
