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

	/*
	 * sets the the the cave according to the Sensors data
	 * (Beacon possible or not)
	 */
	public void setData(Cave cave) {
		for(int y = 0; y <cave.cave.length;y++) {
			for(int x = 0; x<cave.cave[y].length;x++) {
				if(cave.cave[y][x] instanceof Air) {
					if(getDistance(cave.cave[y][x]) <= beaconDistance)
						((Air) cave.cave[y][x]).beaconNotPossible();
				}
			}
		}
		
		cave.set(this,pos);
		if(cave.inCave(closestBeacon.getPos()))
			cave.set(closestBeacon, closestBeacon.pos);
		
	}
	
	/**
	 * 
	 * @param p
	 * @return true when a beacon in p is possible and false if not(shorter distance that closest becon)
	 */
	public boolean positionPossible(int[] p) {
		return getDistance(p)>beaconDistance;
	}
	
}
