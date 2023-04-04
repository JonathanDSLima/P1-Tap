package pageMethods;

import java.net.URL;
import java.util.Scanner;

public class ReadPag {
	
	public static String inspecPag(String url) {
		try {
			URL u = new URL(url);
			Scanner leitor = new Scanner(u.openStream());
			
			StringBuilder pagina = new StringBuilder();
			
			while (leitor.hasNextLine()) {
				pagina.append(leitor.nextLine());
			}
			
			leitor.close();
		
			return pagina.toString();
		} catch (Exception ex) {
			System.err.println(ex);
		}
		return "";
	}

}

