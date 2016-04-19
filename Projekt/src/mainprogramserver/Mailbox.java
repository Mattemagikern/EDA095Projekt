package mainprogramserver;

public class Mailbox {

	String fisk = "";

	public synchronized void setText(String text) {

		if (text != "") {
			while (fisk != null) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		fisk = text;
		notifyAll();

	}

	public synchronized String getTextAndRemove() {
		while (fisk == null) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String tmp = fisk;
		fisk = null;
		notifyAll();
		return tmp;

	}

}
