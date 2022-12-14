package Day14;

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
		int maxX = 0;
		int maxY = 0;
		int minX = Integer.MAX_VALUE;
		//find max y and max x
		for(String p: data) {
			String[] dataSplitted = p.split(" -> ");
			for(String koord: dataSplitted) {
				String[] temp = koord.split(",");
				if(Integer.parseInt(temp[0]) > maxX)
					maxX = Integer.parseInt(temp[0]);
				
				if(Integer.parseInt(temp[0]) < minX)
					minX = Integer.parseInt(temp[0]);
				
				if(Integer.parseInt(temp[1]) > maxY)
					maxY = Integer.parseInt(temp[1]);
			}
		}
		
		Cave cave = new Cave(maxY, maxX,minX);
		
		for(String p: data) {
			cave.addRocks(p);
		}
		
		int erg = cave.letSandFall(0, 500);
		cave.print();
		
		System.out.println(erg);
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