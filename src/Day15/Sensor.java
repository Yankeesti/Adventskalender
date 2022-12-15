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
	
}
