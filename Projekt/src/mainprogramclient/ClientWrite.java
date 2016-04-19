package mainprogramclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClientWrite extends Thread {
	
	Socket socket;
	public ClientWrite(Socket socket){

		this.socket = socket;
	}
	
	public String parseString(String text){
		text = text.substring(3);
		return text;
	}
	
	public void run(){
		try {
			InputStreamReader input = new InputStreamReader(System.in);
			BufferedReader buffReader = new BufferedReader(input);
			OutputStream output = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(output);

			

	
			String character;
			
			
			while (!socket.isClosed()) { 
				character = buffReader.readLine();
				if (character.startsWith("M:")) {
					character = parseString(character);
					writer.println(character);
					writer.flush();
				} else if (character.startsWith("E:")) {
					System.out.println(parseString(character));
				} else if (character.startsWith("Q:")) {
					writer.println(character);
					writer.flush();
					break;
				}
	
			}
		

			socket.close();

		} catch (IOException e) {

		}
	}
}
/*
character = parseString(character);
String txtbk = "You said: " + character;
output.write(txtbk.getBytes());
output.flush();
input.read(character.getBytes());
*/