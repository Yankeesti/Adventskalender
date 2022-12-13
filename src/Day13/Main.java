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
		ArrayList<Package> p = new ArrayList<Package>();
		
		for(int i = 0; i<data.length;i +=3) {
			String[] dataArr = {data[i],data[i+1]};
			p.add(new Package(dataArr));
		}
		long sum = 0;
		for(int i = 0; i< p.size();i++) {
			int erg = p.get(i).orderRight();
			if(erg == 1)
			sum += i+1;
			
			if(erg == 0 || erg == 2) {
				System.out.println(i+1+" is unschlüssig");
			}
		}
		System.out.println(sum);
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
