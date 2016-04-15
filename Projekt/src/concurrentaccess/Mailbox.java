package concurrentaccess;

public class Mailbox {
	StringKeeper keeper;
	
	public Mailbox(){
		keeper = new StringKeeper();
	}
	
	public synchronized void setText(String text){
		keeper.setText(text);
	}
	
	public synchronized String getTextAndRemove(){
		this.notifyAll();
		return keeper.getTextAndRemove();
		
	}
}
