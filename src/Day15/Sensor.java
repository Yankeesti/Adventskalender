package Day15;

import java.util.ArrayList;

public class Sensor extends Structure{
	Beacon closestBeacon;
	int beaconDistance;
	public Sensor(int x, int y, int beaconX, int beaconY) {
		super(x,y);
		closestBeacon = new Beacon(beaconX,beaconY);
		beaconDistance = getDistance(closestBeacon);
	}
	
	public Beacon getBeacon() {return closestBeacon;}
	
	
	/**
	 * 
	 * @param p
	 * @return true when a beacon in p is possible and false if not(shorter distance that closest becon)
	 */
	public boolean positionPossible(int[] p) {
		return getDistance(p)>beaconDistance;
	}
	
	/**
	 * 
	 * @param line - y positon
	 * @return a sortet list small x to large where a beacon is blocked
	 */
	public int[] getBlockedPositions(int line){
		ArrayList<Integer> outPutList = new ArrayList<Integer>();
		
		for(int x = pos[0]-beaconDistance; x <pos[0]+beaconDistance; x++) {
			if(pos[0] == x && line == pos[1]) {
				outPutList.add(x);
				continue;
				}
			if(closestBeacon.getPos()[0] == x && line == closestBeacon.getPos()[1]) {
				continue;
				}
			int[] temp = {x,line};
			if(!positionPossible(temp)) {
				outPutList.add(x);
			}
		}
		int[] outPut = new int[outPutList.size()];
		for(int i = 0; i<outPutList.size();i++) {
			outPut[i] = outPutList.get(i);
		}
		return outPut;
	}
	
//	/**
//	 * 
//	 * @param line - y positon
//	 * @return a sortet list small x to large where a beacon is blocked
//	 */
//	public int[] getBlockedPositions(int line,int minX,int maxX){
//		ArrayList<Integer> outPutList = new ArrayList<Integer>();
//		
//		for(int x = minX; x < maxX; x++) {
//			if(pos[0] == x && line == pos[1]) {
//				outPutList.add(x);
//				continue;
//				}
//			if(closestBeacon.getPos()[0] == x && line == closestBeacon.getPos()[1]) {
//				outPutList.add(x);
//				continue;
//				}
//			int[] temp = {x,line};
//			if(!positionPossible(temp)) {
//				outPutList.add(x);
//			}
//		}
//		int[] outPut = new int[outPutList.size()];
//		for(int i = 0; i<outPutList.size();i++) {
//			outPut[i] = outPutList.get(i);
//		}
//		return outPut;
//	}
	
	/**
	 * Callculates start x and and x position where positon is blocked example:
	 * 	blocked : 4,5,6,7,8,9,10
	 * 	outPut [0] = 5 [1] = 10;
	 * @param line
	 * @param minX
	 * @param maxX
	 * @return start x blocked end x blocked
	 */
	public int[] getBlockedPositions(int line,int minX,int maxX){
		if(lineInReach(line)) {
			int[] outPut = new int[2];
		int lineDistance = getDistance(pos[0], line);
			
			outPut[0] = pos[0] -beaconDistance +lineDistance;
			outPut[1] = pos[0] +beaconDistance -lineDistance;
			
			
			if(outPut[0]< minX)
				outPut[0] = minX;
			if(outPut[1]> maxX)
				outPut[1] = maxX;
			return outPut;
		}else{
		int[] temp = {};
		return temp;}
		
	}
	
	
	
	public boolean lineInReach(int y) {
		int[] temp = {pos[0],y};
		return !positionPossible(temp);
	}
	
}
