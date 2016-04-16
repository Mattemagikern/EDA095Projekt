package mainprogramserver;

import java.io.BufferedOutputStream;
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
			//System.out.println("the text collected from the mailbox to be sent out to client is: " + text);
			//System.out.println("Im alive");
			if (text != "") {
				for (Participant p : participants) {
					//System.out.println(p.getName() + "is connected");
					try {
						PrintWriter out = new PrintWriter(new BufferedOutputStream(p.getSocket().getOutputStream()),
								true);

						out.println(text);

					} catch (IOException e) {

						e.printStackTrace();
					}
				}

				
			}
			
		}
	}
}
