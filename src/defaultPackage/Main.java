package defaultPackage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	static File folder1,file1;
	static ArrayList<Elf>  elfs;
	public static void main(String[] args) throws IOException {
		loadFiles();
		elfs = new ArrayList<Elf>();
		//
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file1));
			Elf currentElf = new Elf();
			elfs.add(currentElf);
			String s;
			while((s = reader.readLine()) != null) {
				if(s.equals("")) {
					currentElf = new Elf();
					elfs.add(currentElf);
				}else {
					currentElf.addCalories(Integer.parseInt(s));
				}
			}
			
			
			for(int i = 0;i<3;i++) {
				for(int i2 = i ; i2 < elfs.size(); i2++) 
				{
					if(elfs.get(i).getCalories() < elfs.get(i2).getCalories()) {
						change(i,i2);
					}
				}
			}
			
			System.out.println(elfs.get(0).getCalories()+elfs.get(1).getCalories()+elfs.get(2).getCalories());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private static void change(int first, int second) {
		Elf temp = elfs.get(first).clone();
		
		elfs.set(first, elfs.get(second));
		
		elfs.set(second, temp);
		
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
