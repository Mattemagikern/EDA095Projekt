package mainprogramserver;

public class Postman extends Thread {
	Mailbox box;
	public Postman(Mailbox box){
		this.box = box;
	}
	
	public void run(){
		
		while(true){
			System.out.println(box.getTextAndRemove());
			try {
				long random = (long) (100*Math.random());
				Thread.sleep(random);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
