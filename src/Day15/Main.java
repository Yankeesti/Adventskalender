package Day15;

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
		ArrayList<Sensor> sensors = new ArrayList<Sensor>();
		int maxX = 0;
		int maxY = 0;
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		for(String p: data) {
			Sensor temp = getSensor(p);
			sensors.add(temp);
			int[] sensorPos = temp.getPos();
			int[] beaconPos = temp.getBeacon().getPos();
			
			if(sensorPos[0] > maxX)
				maxX = sensorPos[0];
			if(beaconPos[0] > maxX)
				maxX = beaconPos[0];
			
			if(sensorPos[1] > maxY)
				maxY = sensorPos[1];
			if(beaconPos[1] > maxY)
				maxY = beaconPos[1];
			
			if(sensorPos[1] < minY)
				minY = sensorPos[1];
			if(beaconPos[1] < minY)
				minY = beaconPos[1];
			
			if(sensorPos[0] < minX)
				minX = sensorPos[0];
			if(beaconPos[0] < minX)
				minX = beaconPos[0];
		}
		
		Cave cave = new Cave(maxX, maxY, minX ,minY);
		for(int i = 0; i< sensors.size();i++) {
			cave.addSenosor(sensors.get(i));
		}
		System.out.println(cave.getBeaconexcluded(10));
	}
	
	/**
	 * creates a Senosor and returns it
	 * @param data
	 */
	public static Sensor getSensor(String data) {
		String[] dataSplit = data.split(" ");
		int[] sensorPos = new int[2];
		int[] beaconPos = new int[2];
		//find x
		sensorPos[0] = Integer.parseInt(dataSplit[2].split(",")[0].split("=")[1]);
		sensorPos[1] = Integer.parseInt(dataSplit[3].split(":")[0].split("=")[1]);
		
		beaconPos[0] = Integer.parseInt(dataSplit[8].split(",")[0].split("=")[1]);
		beaconPos[1] = Integer.parseInt(dataSplit[9].split("=")[1]);
		
		Sensor outPut = new Sensor(sensorPos[0], sensorPos[1], beaconPos[0], beaconPos[1]);
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
