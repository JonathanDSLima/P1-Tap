import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.Html;

public class SerializableForPage {
	
	public static void record(ArrayList<Html> htmls) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(
					new File("htmls.txt")));
			oos.writeObject(htmls);
			oos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Html> save() {
		try {
			
			ObjectInputStream ois = new ObjectInputStream(
					new FileInputStream(
					new File("produtos.txt")));
			@SuppressWarnings("unchecked")
			ArrayList<Html> htmls = (ArrayList<Html>)ois.readObject();
			ois.close();
			return htmls;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return new ArrayList<Html>();
	}
	
}
