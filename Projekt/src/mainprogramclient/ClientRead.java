package mainprogramclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientRead extends Thread {
	Socket socket;

	public ClientRead(Socket socket){
		this.socket = socket;
	}
	
	public String parseString(String text){
		text = text.substring(3);
		return text;
	}
	
	public void run(){
		try {
			InputStream input = socket.getInputStream();

			InputStreamReader reader = new InputStreamReader(input); 
			BufferedReader buffReader = new BufferedReader(reader);
			String character;
			
			while (!socket.isClosed()){
				character = buffReader.readLine();
				System.out.println(character);

			}

			socket.close();

		} catch (IOException e) {

		}
	}
}
