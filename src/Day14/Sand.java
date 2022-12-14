package Day14;

public class Sand extends Structure{
	Cave cave;
	int[] startPosition;
	public Sand(int startPositionY , int startPositionX, Cave cave) {
		setPos(startPositionY, startPositionX);
		this.cave =cave;
		startPosition = new int[2];
		startPosition[0] = startPositionY;
		startPosition[1] = startPositionX;
	}

	/**
	 * lets the sand fall 
	 * @return true if final position is not the the startPosition and false if it is
	 */
	public boolean fall() {
		if(cave.positionFree(pos[0]+1,pos[1])) // fall down
		{
			setY(pos[0]+1);
			 return fall();
		}else if(cave.positionFree(pos[0]+1,pos[1]-1)) {//fall down left
			setY(pos[0]+1);
			setX(pos[1]-1);
			return fall();
		}else if(cave.positionFree(pos[0]+1,pos[1]+1)) {//fall down right
			setY(pos[0]+1);
			setX(pos[1]+1);
			return fall();
		}else {//final positon
			if(pos[0] == startPosition[0] && pos[1] == startPosition[1]) {
				cave.setSand(pos, this);
				return false;}
			cave.setSand(pos, this);
			return true;
		}
	}

	private void setY(int newY) {
		pos[0] = newY;
	}

	private void setX(int newX) {
		pos[1] = newX;
	}
}
