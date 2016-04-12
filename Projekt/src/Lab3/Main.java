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
		ServerSocket server;
		try {
			server = new ServerSocket(30000);
			boolean loop = true;
			while(loop){
				Socket connection = server.accept();
				ServerThreads client = new ServerThreads(connection);
				client.start();
				
			
			}
			server.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
		
	
		
			
	
}
