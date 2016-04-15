package threadcoordination;

public class ThreadCreator {

	public static void main(String[] args) {
	
		for (int i = 0; i < 10; i++){
			NameWriter t = new NameWriter("namegoeshere"+i);
			t.start();
		}

	}

}
