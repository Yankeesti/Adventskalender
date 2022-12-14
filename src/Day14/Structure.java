package Day14;

public class Structure {
	protected boolean finalPosition;
	protected int[] pos;
	
	public Structure() {
		pos = new int[2];
		finalPosition = false;
	}
	
	public int[] getPos() {return pos;}
	public boolean getFinalPosition() {return finalPosition;}
	protected void setPos(int y, int x) {
		pos[0] = y;
		pos[1] = x;
	}
}
