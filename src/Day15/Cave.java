package Day15;

import java.util.ArrayList;

import Day14.Rock;
import Day14.Sand;

public class Cave {
	Structure[][] cave;
	int maxX,maxY,minX,minY;
	ArrayList<Sensor> sensors;
	public Cave(int maxX, int maxY,int  minX , int minY) {
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
		this.minX = minX;
		
		cave = new Structure[maxY-minY+1][maxX-minX+1];
		for(int y = 0; y<cave.length;y++) {
			for(int x = 0; x<cave[0].length;x++) {
				cave[y][x] = new Air(x+minX,y+minY);
			}
		}
		sensors = new ArrayList<>();
	}
	
	/**
	 * adds a Sensor and its beacon to the cave,
	 * also sets beaconPossible in the Sensors Aria according o its data
	 */
	public void addSenosor(Sensor p) {
		p.setData(this);
		sensors.add(p);
	}
	
	public void print() {
		
		for(int y = 0 ; y<cave.length;y++) {	
		for(int x = 0 ; x< cave[y].length;x++) {
			if(cave[y][x] instanceof Air) {
				if(((Air)cave[y][x]).beaconPossible)
				System.out.print(".");
				else
					System.out.print("#");
			}else if(cave[y][x] instanceof Sensor){
				System.out.print("S");
			}else if(cave[y][x] instanceof Beacon){
				System.out.print("B");
			}
		}
		System.out.println();
	}
	}
	
	public boolean inCave(int[] pos) {
			return pos[0] >= minX  && pos[0] <= maxX && pos[1] >= minY && pos[1] <= maxY;
	}

	
	public void set(Structure p, int pos[]) {
		cave[pos[1]-minY][pos[0]-minX] = p;
	}
	
	public void printLine(int y) {
		for(int x = 0; x<cave[y].length;x++) {
			if(cave[y][x] instanceof Air) {
				if(((Air)cave[y][x]).beaconPossible)
				System.out.print(".");
				else
					System.out.print("#");
			}else if(cave[y][x] instanceof Sensor){
				System.out.print("S");
			}else if(cave[y][x] instanceof Beacon){
				System.out.print("B");
			}
		}
		System.out.println();
	}
	
	/**
	 * Calculates how many positons there are in line line whre no beacon is possible
	 * @param line
	 * @return number of positions where beacon is impossible
	 */
	public int getBeaconexcluded(int line) {
		int count = 0;
//		for(int x = 0; x<cave[line-minY].length;x++) {
//			if(cave[line-minY][x] instanceof Sensor) {
//				count ++;
//			}else if(cave[line-minY][x] instanceof Air){
//				if(!((Air)cave[line-minY][x]).beaconPossible)
//					count ++;
//			}
//		}
		Position:
		for(int x = -500+minX;x<maxX+500;x++) {
			int[] aktPos = {x,line};
			for(int i = 0; i< sensors.size();i++) {
				if(!inCave(aktPos)) {
					if(!sensors.get(i).positionPossible(aktPos)) {
						count ++;
						continue Position;
						}
				}else {
					if(cave[aktPos[1]-minY][aktPos[0]-minX] instanceof Sensor) {
						count++;
						continue Position;
					} 
						
					else if(cave[aktPos[1]-minY][aktPos[0]-minX] instanceof Air){
						if(!sensors.get(i).positionPossible(aktPos)) {
							count ++;
							continue Position;
							}
					} 
				}
					
			}
		}
		return count;
	}
}
