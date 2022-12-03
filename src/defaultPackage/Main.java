package defaultPackage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	static File folder1,file1;
	static ArrayList<Rucksack>  rucksaecke;
	public static void main(String[] args) throws IOException {
		loadFiles();
		rucksaecke = new ArrayList<Rucksack>();
		String[] data = getData(file1);
		for(String p:data) {
			rucksaecke.add(new Rucksack(p));
		}
		int outPut = 0;
		for(int i = 0; i<rucksaecke.size();i++) {
			outPut += rucksaecke.get(i).findDouble();
		}
		
		System.out.println(outPut);
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
	
	/**
	 * 
	 * @param p - String with the following syntax play opponent+" "+response
	 * @return score for this round
	 */
	private static int rockPaperScisorScore(String[] p) {
		String[] play = p;
		int outPut = 0;
		switch (play[1]) {
		case "X":
			outPut+=1;
			if(play[0].equals("C"))
				outPut += 6;
			else if(play[0].equals("A"))
				outPut += 3;
			break;
		case "Y":
			outPut+=2;
			if(play[0].equals("A"))
				outPut += 6;
			else if(play[0].equals("B"))
				outPut += 3;
			break;
		case "Z":
			outPut+=3;
			if(play[0].equals("B"))
				outPut += 6;
			else if(play[0].equals("C"))
				outPut += 3;
			break;
			}
			
		
		return outPut;
	}
	
	private static int rockPaperScisorScorePart2(String[] p) {
		if(p[0].equals("A")) {//Rock
			switch (p[1]) {
			case "X": // lose
				p[1] = "Z"; //Scicor
				return rockPaperScisorScore(p);
			case "Y":	//draw
				p[1] = "X"; //Rock
				return rockPaperScisorScore(p);
			case "Z": // Win
				p[1] = "Y"; //Paper
				return rockPaperScisorScore(p);
			}
		}
		
		if(p[0].equals("B")) {//Paper
			switch (p[1]) {
			case "X": // lose
				p[1] = "X"; //Rock
				return rockPaperScisorScore(p);
			case "Y":	//draw
				p[1] = "Y"; //paper
				return rockPaperScisorScore(p);
			case "Z": // Win
				p[1] = "Z"; //Scicor
				return rockPaperScisorScore(p);
			}
		}
		
		if(p[0].equals("C")) {//Scicor
			switch (p[1]) {
			case "X": // lose
				p[1] = "Y"; //Paper
				return rockPaperScisorScore(p);
			case "Y":	//draw
				p[1] = "Z"; //Scicor
				return rockPaperScisorScore(p);
			case "Z": // Win
				p[1] = "X"; //Rock
				return rockPaperScisorScore(p);
			}
		}
		
		return 0;
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
