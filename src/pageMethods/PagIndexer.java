package pageMethods;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.Html;

public class PagIndexer {
	
	private static 	Map<String, Set<String>> index = new HashMap<>();
	private static  Set<String> indexByWork = null;
	static ArrayList<Html> site = new ArrayList<Html>();
	
	public static boolean indexPag(String texto) {
			boolean hasLink = false;
			String link = texto;
			if (index.containsKey(link)) {
				indexByWork = index.get(link);
				hasLink = true ;
			} else {
				indexByWork = new HashSet<>();
				index.put(link, indexByWork);
				hasLink = false;
			}
			indexByWork.add(texto);
			return hasLink;
	}
	public static ArrayList<Html> getSite() {
		return site;
	}
	public static void addSite(ArrayList<Html> site) {
		PagIndexer.site = site;
	}

}
