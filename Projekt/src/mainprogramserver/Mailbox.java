package mainprogramserver;

public class Mailbox {
	//StringKeeper keeper;
	String fisk = "";
	int nbrOfThreadsInQueue = 0;
	
	public Mailbox(){
		//keeper = new StringKeeper();
	}
	

	
	public synchronized void setText(String text){
		
			if (text != "") {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			fisk = text;
			//System.out.println("The mailbos text is" + text);
		//keeper.setText(text);
	}
	
	public  synchronized String getTextAndRemove(){
		String tmp = fisk;
		fisk = "";
		notify();
		return tmp;
		//return keeper.getTextAndRemove();
		
	}
	
}