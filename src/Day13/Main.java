package Day13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	static File folder1,file1;
	public static void main(String[] args) throws IOException {
		loadFiles();
		String[] data = getData(file1);
		ArrayList<List> p = new ArrayList<List>();
		List dividerOne = new List("[[2]]", null, 0);
		List dividerTwo = new List("[[6]]", null, 0);
		p.add(dividerOne);
		p.add(dividerTwo);
		for(int i = 0; i<data.length;i++) {
			if(data[i].length() > 0) {
				List temp1 = new List(data[i], null, i);
				p.add(temp1);
			}
		}
		
		p = sort(p);
		
		System.out.println((p.indexOf(dividerTwo)+1)*(p.indexOf(dividerOne)+1));
	}
	
	
	private static ArrayList<List> sort(ArrayList<List> p) {
		ArrayList<List> outPut = new ArrayList<List>();
		
		while(!p.isEmpty()) {
			List lowest = p.get(0);
			int lowestIndex = 0;
			for(int i = 0; i<p.size();i++) {
				if(List.orderdRight(lowest, p.get(i)) == -1) {
					lowest = p.get(i);
					lowestIndex = i;
				}
			}
			p.remove(lowestIndex);
			outPut.add(lowest);
		}
		return outPut;
	}


	private static String[] getData(File f) {
		ArrayList<String> data = new ArrayList<String>();
		BufferedReader reader = null; 
		try {
			reader = new BufferedReader(new FileReader(file1));
			
			String s;
			while((s = reader.readLine()) != null) {
				data.add(s);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			
		}finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String[] outPut = new String[data.size()];
		for(int i = 0; i<outPut.length;i++) {
			outPut[i] = data.get(i);
		} 
		return outPut;
	}
	
	private static void loadFiles() {
		folder1 = new File("data");
		file1 = new File("data/data.txt");
		
		if(folder1.exists()) {
			System.out.println("Ordner existiert");
		}else {
			folder1.mkdirs();
		}
		
		if(file1.exists()) {
			System.out.println("Datei existiert");
		}else {
			try {
				file1.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
