package mainprogramclient;

import java.io.IOException;
import java.net.Socket;


public class ClientCreator {

	public void startServer() {

		try {

			Socket socket = new Socket("192.168.0.11", 40000);
			ClientWrite clientWrite = new ClientWrite(socket);
			ClientRead clientRead = new ClientRead(socket);
			clientWrite.start();
			clientRead.start();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		ClientCreator creator = new ClientCreator();
		creator.startServer();

	}

}
