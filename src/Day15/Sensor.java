package Day15;

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
	
}
