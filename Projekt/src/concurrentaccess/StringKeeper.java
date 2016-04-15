package concurrentaccess;

public class StringKeeper {
	String text = "";
	
	public synchronized void setText(String text){
		this.text = text;
	}
	
	public synchronized String getTextAndRemove(){
		
		String tmp = text;
		text = "";
		return tmp;
		
	}
	
}
