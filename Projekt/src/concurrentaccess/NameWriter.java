package concurrentaccess;

public class NameWriter extends Thread {
	
	String name;
	Mailbox box;
	
	public NameWriter(String name, Mailbox box){
		this.name = name;
		this.box = box;
	}
	
	public void run(){
		
		for (int i = 0; i < 5; i++){
			box.keeper.setText(name);
			
			long random = (long) (1000*Math.random());
			try {
				Thread.sleep(random);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				this.wait(15000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
			
	}
	
}
