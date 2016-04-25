import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class SynchronizedURLs {
	private LinkedList<URL> remainingURLs;
	private Set<URL> traversedURLs;
	public SynchronizedURLs(LinkedList<URL> remainingURLs){
		this.remainingURLs = remainingURLs;
		this.traversedURLs = new HashSet<URL>();
		
	}
	
	synchronized URL getFirstURL(){
		while(remainingURLs.size() <= 0){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		URL url = remainingURLs.poll();
		return url;
	}
	
	synchronized void addTraversedURL(URL url){
		traversedURLs.add(url);
		
	}
	
	synchronized int getAmountOfURLsTraversed(){
		return traversedURLs.size();
	}
	
	synchronized boolean checkIfTraversedContains(URL url){
		return traversedURLs.contains(url);
	}
	
	synchronized void addRemainingURL(URL url){
		remainingURLs.add(url);
		notifyAll();
	}
	
	synchronized Set<URL> getTraversedList(){
		return traversedURLs;
	}
	
}
