package mainprogramserver;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ServerWrite extends Thread{
	Mailbox box;
	ArrayList<Participant> participants;
	//Postman man;
	
	public ServerWrite(Mailbox box, ArrayList<Participant> participants ){
		this.box = box;
		this.participants = participants;
		//this.man = man;
	}
	
	public void run(){
		
		while (true){
			String text = box.getTextAndRemove();
				System.out.println("Im alive");
				for (Participant p : participants) {
					try {
						OutputStream output = p.getSocket().getOutputStream();
						PrintWriter writer = new PrintWriter(output);
						writer.println(text);

					} catch (IOException e) {

						e.printStackTrace();
					}
				}
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}
}
