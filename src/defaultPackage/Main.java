package defaultPackage;
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
		
		Map map = new Map(data);
		
		System.out.println(map.getStepsWithbestStartingPoint());
		
	}
	
	private static long getMonkeyBusines(Monkey[] monkeys) {
		int highestIndex = -1;
		int secondhighestIndex = -1;
		
		for(int i = 0; i< monkeys.length;i++) {
			if(highestIndex == -1) {
				highestIndex = i;
				continue;
			}else if(secondhighestIndex == -1) {
				if(monkeys[highestIndex].inspections < monkeys[i].inspections) {
					secondhighestIndex = highestIndex+0;
					highestIndex = i;
					continue;
				}else
					secondhighestIndex = i;
			}else {
				if(monkeys[secondhighestIndex].inspections < monkeys[i].inspections) {
					if(monkeys[highestIndex].inspections < monkeys[i].inspections) {
						secondhighestIndex = highestIndex+0;
						highestIndex = i;
						continue;
					}else
					secondhighestIndex = i;
				}
			}
		}
		long outPut = (long)(monkeys[highestIndex].inspections)*(long)(monkeys[secondhighestIndex].inspections);
		return outPut;
	}
	

	private static int scenicScore(int[]treeLocation, int[][] treeArray) {
		int treeHight = treeArray[treeLocation[0]][treeLocation[1]];
		int[] viewingDistances = new int[4]; //[0]=North [1]=South [2]=West [3]=East
		
		//North
		if(treeLocation[0] == 0) //Tree is at Top end
			return 0;
		else
			for(int y = treeLocation[0]-1 ; y>= 0;y--) {
				if(treeArray[y][treeLocation[1]]>=treeHight) {
					viewingDistances[0] ++;
					break;
				}
				viewingDistances[0] ++;
			}
		
		//South
				if(treeLocation[0] == treeArray.length-1) //Tree is at bottom end
					return 0;
				else
					for(int y = treeLocation[0]+1 ; y<treeArray.length ;y++) {
						if(treeArray[y][treeLocation[1]]>=treeHight) {
							viewingDistances[1] ++;
							break;
						}
						viewingDistances[1] ++;
					}
				
		//West
		if(treeLocation[1] == 0) //Tree is at Left end
			return 0;
		else
			for(int x = treeLocation[1]-1 ; x>= 0;x--) {
				if(treeArray[treeLocation[0]][x]>=treeHight) {
					viewingDistances[2] ++;
					break;
				}
				viewingDistances[2] ++;
			}
		
		//East
				if(treeLocation[1] == treeArray[0].length-1) //Tree is at bottom
					return 0;
				else
					for(int x = treeLocation[1]+1 ; x<treeArray.length ;x++) {
						if(treeArray[treeLocation[0]][x]>=treeHight) {
							viewingDistances[3] ++;
							break;
						}
						viewingDistances[3] ++;
					}	
				
				int outPut = 1;
				for(int p : viewingDistances)
					outPut*= p;
				return outPut;
		
	}
	
	private static boolean visible(int[] treeLocation, int[][] treeArray) {
		int treeHight = treeArray[treeLocation[0]][treeLocation[1]];
		
		if(treeLocation[0] == 0 || treeLocation[0] == treeArray.length-1) {
			return true;
		}
		
		if(treeLocation[1] == 0 || treeLocation[1] == treeArray[0].length-1) {
			return true;
		}
		
		//North
		for(int y = 0; y<treeLocation[0] ; y++) {
			if(treeArray[y][treeLocation[1]] >= treeHight)
				break;
			if(y == treeLocation[0]-1)
				return true;
		}
		
		//south
		for(int y = treeArray.length-1; y>treeLocation[0] ; y--) {
			if(treeArray[y][treeLocation[1]] >= treeHight)
				break;
			if(y == treeLocation[0]+1)
				return true;
		}
		
		//West
		for(int x = 0; x<treeLocation[1] ; x++) {
			if(treeArray[treeLocation[0]][x] >= treeHight)
				break;
			if(x == treeLocation[1]-1)
				return true;
		}
		
		//East
		for(int x = treeArray[0].length-1; x>treeLocation[1] ; x--) {
			if(treeArray[treeLocation[0]][x] >= treeHight)
				break;
			if(x == treeLocation[1]+1)
				return true;
		}
		
		return false;
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
