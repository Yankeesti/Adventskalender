package defaultPackage;

import java.util.ArrayList;

public class Position {
	int[] kord;//Y|X
	Position prePosition; //the position that comes before this position on th fastes way
	int steps;
	int height;
	public Position(int[] kord,char height) {
		this.kord = kord;
		steps = -1;
		if(height == 'S') {
			this.height = 1;
			steps = 0;
		}else if(height == 'E') {
			this.height = ((int) 'z') -96;
			steps = Integer.MAX_VALUE;
		}else {
			this.height = ((int) height) -96;
			steps = Integer.MAX_VALUE;
		}
	}
	
	/**
	 * 
	 * @return the steps it takes from the begining to get here
	 */
	public int getSteps() {
		return steps;
	}
	
	public int getHeight() {
		return height;
	}
	
	public boolean climbable(Position startPos) {
		return height-startPos.getHeight() <= 1;
	}
	
	public void setPre(Position pre) {
		prePosition = pre;
	}

	public void setSteps(int i) {
		steps = i;
	}
}
