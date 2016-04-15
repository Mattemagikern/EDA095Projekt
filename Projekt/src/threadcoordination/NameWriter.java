package threadcoordination;

public class NameWriter extends Thread {
	
	String name;
	
	public NameWriter(String name){
		this.name = name;
	}
	
	public void run(){
		
		for (int i = 0; i < 5; i++){
			System.out.println(name);
			long random = (long) (10000*Math.random());
			try {
				Thread.sleep(random);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			
	}
	
}
