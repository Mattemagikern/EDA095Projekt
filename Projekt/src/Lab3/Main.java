package Lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
	public static void main(String args[]){
		
		try (ServerSocket server = new ServerSocket(3000)){
			
			while(true){
				try (Socket connection = server.accept()){
					InputStream input = connection.getInputStream();
					OutputStream output = connection.getOutputStream();
					InetAddress clientAddress = connection.getInetAddress();
					System.out.println(clientAddress.getHostAddress());
					
					StringBuilder builder = new StringBuilder();
					InputStreamReader reader = new InputStreamReader(input); //converts bytes into readable chars
					BufferedReader buffReader = new BufferedReader(reader);
					String character;
					while ((character = buffReader.readLine()) != null){ //reads next character and checks so we're not at the end
						builder.append(character);
					}
					System.out.println(builder.toString());
					
					String test = "fish";
					output.write(test.getBytes());
					output.flush();
					
					
				} catch (IOException e){
					
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
		
		
			
	
}
