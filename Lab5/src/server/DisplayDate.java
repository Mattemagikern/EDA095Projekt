package server;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class DisplayDate {

	public static void main(String args[]){
		DisplayDate disp = new DisplayDate();
		disp.start();
	}
	
	public void start(){
		System.out.println(getLocalDate());
		
	}
	
	public String getLocalDate(){
		Date curDate = new Date();
		return DateFormat.getDateInstance().format(curDate);
	}
	
	public String getLocalTime(){
		Date curDate = new Date();
		return DateFormat.getDateTimeInstance().format(curDate);
	}
}
