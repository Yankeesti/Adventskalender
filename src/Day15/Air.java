package Day15;

public class Air extends Structure{
	boolean beaconPossible;
	public Air(int x, int y) {
		super(x,y);
		beaconPossible = true;
	}
	
	public void beaconNotPossible() {beaconPossible = false;}
}
