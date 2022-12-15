package Day15;

public class Structure {
	protected int[] pos;// x|y
	
	public Structure() {
		pos = new int[2];
	}
	
	public Structure(int x, int y) {
		this();
		pos[0] = x;
		pos[1] = y;
	}
	
	
	public int[] getPos() {return pos;}
	
	public int getDistance(Structure p) {
		int[] pPos = p.getPos();
		return Math.abs(pos[0]-pPos[0])+Math.abs(pos[1]-pPos[1]);
	}
	
	public int getDistance(int[] p) {
		
		return Math.abs(pos[0]-p[0])+Math.abs(pos[1]-p[1]);
	}
}