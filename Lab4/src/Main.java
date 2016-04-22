import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

	public static int counter;
	public static List<URL> doublet = new ArrayList<URL>();
	
	public static void main(String[] args) {
		
		Main main = new Main();
		main.search();
	}
	
	public void search(){
		Scanner input = new Scanner(System.in);
		String stringurl = input.nextLine();
		URL url = null;
		List<URL> urls = null;
		try {
			url = new URL(stringurl);
			urls = new ArrayList<URL>();
		} catch (MalformedURLException e) {

			e.printStackTrace();
		}
//		List<URL> urls2 = getPageUrls(url.toString());
//		for (URL e : urls2){
//			System.out.println(e.toString());
//		}
		search(url, counter);
		//urls = getPageUrls(url.toString());
		int count = 0;
		System.out.println(doublet.size());
		if (doublet != null) {
			for (URL e : doublet) {
				count++;
				System.out.println(e.toString() + " " + count);
			}
		}
	}
	
//	private List<URL> search(URL url, List<URL> urls, int counter){
//		List<URL> lista = getPageUrls(url.toString());
//		System.out.println(urls);
//		Queue<URL> que = new LinkedList<URL>();
//
//		for (URL e : lista){
//			que.add(e);
//		}
//		
//		if (urls.size() >= 50 || lista.size() == 0){
//			return urls;
//		}
//		
//		while (!que.isEmpty() && urls.size() < 50){
//			URL fat = que.poll();
//			boolean alreadyExists = false;			
//			for (URL e : urls){
//				if (e.toString().equals(fat.toString())){
//					alreadyExists = true;
//				}
//			}
//	
//			if (!alreadyExists){
//				urls.add(url);
//				counter++;
//				search(fat, urls, counter);
//			}
//
//		}
//		
//		return urls;
//	}
//	
	private void search(URL url, int counter){
		List<URL> lista = getPageUrls(url.toString());
//		System.out.println(doublet.size());
//		System.out.println(lista);
//		System.out.println(url);
		if (doublet.size() >= 200 || lista.size() == 0 || doublet.contains(url)){
			System.out.println(doublet.size()+ " " + lista.size());
			return;
		}
		
			
			counter++;
			
			//urls.add(url);
			doublet.add(url);
		
		
		
		System.out.println(lista);
		System.out.println(doublet.size());
		
		for (URL u : lista){
			if (doublet.size() >= 200){
				return;
			}
			System.out.println(u);
			
			
				search(u, counter);
				
			
		}
		
	
	}
	

	public List<URL> getPageUrls(String startURL) {
		List<URL> lista = new ArrayList<URL>();
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
				
				if (linkAbsHref != ""){
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
