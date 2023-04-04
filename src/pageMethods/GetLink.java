package pageMethods;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Html;

public class GetLink {
	
	private static ExecutorService threadPool = Executors.newFixedThreadPool(4);
	
	//Expressão regular para validar os links
	private static final String HTTPS_REGEX= "/https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&=]*)/";

	//Função responsável por buscar o liks dentro da página
	public static String findHtml(String text) {
		int urlFound = 0;	
		Pattern htmlPattern = Pattern.compile(HTTPS_REGEX);
		Matcher htmlMatcher = htmlPattern.matcher(text);
		
		while ( urlFound < 10 ) {	
			ReadPag read = new ReadPag();
			Scanner reader = new Scanner(text);

			while (reader.hasNextLine()) {
				if (htmlMatcher.find()) {
					
					if(PagIndexer.indexPag(htmlMatcher.group(1))){
						urlFound++;
						System.out.println(htmlMatcher.group(1));
						
						//Criação da thead e recursividade para cada novo link que encontrar
						threadPool.submit(() -> {	
							
							String newHtmlFind = read.inspecPag(htmlMatcher.group(1));
							new Html (htmlMatcher.group(1), newHtmlFind);
							findHtml(newHtmlFind);
							PagIndexer.indexPag(htmlMatcher.group(1));
							System.out.println("no site" + text + "encontrei esse link" );
							System.out.println("urlEncontrados" + htmlMatcher.group(1));
							return htmlMatcher.group(1);
						});
					}
				}
			}
			reader.close();
		}
		return "";
	}
	
	//Função responsável por encerrar a thread
	public void encerra() {
		try {
			Thread.sleep(500);
		} 
		catch (Exception ex) {}
		threadPool.shutdown();
		
		try {
			threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
		} 
		catch (InterruptedException e) {}
	}

}
