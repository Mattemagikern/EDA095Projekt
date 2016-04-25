import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Processor extends Thread {
	SynchronizedURLs synch;

	public Processor(SynchronizedURLs synch) {
		this.synch = synch;
	}

	public void run() {
		search();
	}

	private void search() {
		
		while (synch.getAmountOfURLsTraversed() <= 2000) {
			URL url = synch.getFirstURL();
			
			if (synch.checkIfTraversedContains(url)){
				System.out.println(url.toString() + " already existed, fetching new one");
				continue;
			}
			synch.addTraversedURL(url);
			
			System.out.println(url.toString() + "added to traversedURLs - " + synch.getAmountOfURLsTraversed());
			LinkedList<URL> lista = getPageUrls(url.toString());
			// System.out.println(lista);
			// System.out.println(doublet.size());
			
			for (URL u : lista) {
				if (synch.getAmountOfURLsTraversed() >= 2000) {
					return;
				}

				System.out.println(u.toString() + " added to remainingURLs");
				synch.addRemainingURL(u);

			}
		}

	}

	public LinkedList<URL> getPageUrls(String startURL) {
		LinkedList<URL> lista = new LinkedList<URL>();
		try {
			URL url = new URL(startURL);
			InputStream is = url.openStream();
			Document doc = Jsoup.parse(is, "UTF-8", startURL);
			Elements base = doc.getElementsByTag("base");

			Elements links = doc.getElementsByTag("a");
			for (Element link : links) {
				String linkHref = link.attr("href");
				String linkAbsHref = link.attr("abs:href");
				String linkText = link.text();

				if (linkAbsHref != "") {
					URL urlfish = new URL(linkAbsHref);
					lista.add(urlfish);
				}
			}
			is.close();
		} catch (Exception e) {

		}

		return lista;
	}
}
