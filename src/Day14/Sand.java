package Day14;

public class Sand extends Structure{
	Cave cave;
	public Sand(int startPositionY , int startzPositionX, Cave cave) {
		setPos(startPositionY, startzPositionX);
		this.cave =cave;
	}
	
	/**
	 * lets the sand fall 
	 * @return true if final position is set and false if falling in to void
	 */
	public boolean fall() {
		if(!cave.inCave(this))
			return false;
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
