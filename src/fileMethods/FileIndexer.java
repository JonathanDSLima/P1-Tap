package fileMethods;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class FileIndexer {
	
	private static Map<String, Set<String>> index = new HashMap<>();
	private static Set<String> STOP_WORDS = new HashSet<>(Arrays.asList(new String[]{"A", "O", "E", "AS", "OS", "UM", "UMA", "UNS", "UMAS"}));
	
	private static String getContentFile(File text) throws FileNotFoundException {
		Scanner reader = new Scanner(text);
		StringBuilder builder = new StringBuilder();
		
		while (reader.hasNextLine()) {
			String linha = reader.nextLine();
			builder.append(linha + "\n");
		}
		reader.close();
		return builder.toString();
	}
	
	public static boolean isStopWord(String work) {
		if (work.length() <= 3) {
			return true;
		}
		
		return STOP_WORDS.contains(work);
	}
	
	public static String workToKey(String work) {
		return work.toUpperCase();
	}
	
	public static void indexFile(File text) throws FileNotFoundException {
		String conteudo = getContentFile(text);
		String[] palavras = conteudo.split("\\s+");
		
		Arrays.stream(palavras)
		.map((p) -> workToKey(p))
		.filter((p) -> !isStopWord(p))
		.forEach((work) -> {		
			work = workToKey(work);
			
			Set<String> indexByWork = null;
			
			if (index.containsKey(work)) {
				
				indexByWork = index.get(work);
			} else {
				indexByWork = new HashSet<>();
				index.put(work, indexByWork);
			}
			
			indexByWork.add(text.getAbsolutePath());
		});
	}
	
	public static Set<String> find(String work) {
		String chave = workToKey(work);
		return index.get(chave);
	}
	
	public static void indexFolder(File source) {
		for (File f : source.listFiles()) {
			if (f.isFile()) {
				try {
					indexFile(f);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			} else if (f.isDirectory()) {
				indexFolder(f);
			}
		}
	}

}
