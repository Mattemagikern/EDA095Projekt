package concurrentaccess;

public class ThreadCreator {

	public static void main(String[] args) {
		Mailbox box = new Mailbox();
		Postman man = new Postman(box);
		man.start();
		for (int i = 1; i < 11; i++){
			NameWriter t = new NameWriter("namegoeshere"+i, box);
			t.start();
		}

	}

}
