package Day14;

public class Cave {
	Structure[][] cave;
	int minWidth,maxWidth;
	public Cave(int maxDeepth , int maxWidth, int minWidth) {
		int width = maxWidth-minWidth;
		cave = new Structure[maxDeepth+1][width+1];
		this.minWidth = minWidth;
		this.maxWidth = maxWidth;
	}
	
	public void addRocks(String rockData) {
		String[] rockDataSplitted = rockData.split(" -> ");
		int positions[][] = new int[rockDataSplitted.length][2];
		
		for(int i =  0;i<rockDataSplitted.length;i++) {
			String[] temp = rockDataSplitted[i].split(",");
			positions[i][0] = Integer.parseInt(temp[1]); // set Y Positon
			positions[i][1] = Integer.parseInt(temp[0]);// set X Positon
		}
		
		for(int i = 0; i<rockDataSplitted.length-1;i++) {
			setRockLine(positions[i],positions[i+1]);
		}
	}
	
	/**
	 *  Let's Sand fall from the starting positon Y and X
	 * @param startY
	 * @param startX
	 * @return how much Sand can fall and find a final position
	 */
	public int letSandFall(int startY, int startX) {
		startX -= minWidth;
		int count = 0;
		while(true) {
			Sand temp = new Sand(startY,startX,this);
			
			if(!temp.fall()) {
				break;
			}
			count ++;
		}
		return count;
	}
	
	/**
	 * sets a Rock Line from posOne to PosTwo
	 * @param posOne
	 * @param posTwo
	 */
	private void setRockLine(int[] posOne, int[] posTwo) {
		if(posOne[0] == posTwo[0]) {//same  Y positions
			if(posOne[1] > posTwo[1]) {//so pos one is always on the left side
				setRockLine(posTwo, posOne);
			}else {
				for(int i = posOne[1]-minWidth; i<= posTwo[1]-minWidth; i++) {
					cave[posOne[0]][i] = new Rock(posOne[0], i);
				}
			}
		}else if(posOne[1] == posTwo[1]) {//same X Positon
			if(posOne[0] > posTwo[0]) {//so pos one is always above posTwo
				setRockLine(posTwo, posOne);
			}else {
				for(int i = posOne[0]; i< posTwo[0]; i++) {
					cave[i][posOne[1]-minWidth] = new Rock(i,posOne[1]);
				}
			}
		}
	}
	
	public void print() {
			for(int y = 0 ; y<cave.length;y++) {
			for(int x = 0 ; x< cave[y].length;x++) {
				if(cave[y][x] == null) {
					System.out.print(".");
				}else if(cave[y][x] instanceof Rock){
					System.out.print("#");
				}else if(cave[y][x] instanceof Sand){
					System.out.print("o");
				}
			}
			System.out.println();
		}
	}
	
	public boolean positionFree(int[] pos) {
		if(pos[0] >= cave.length || pos[0] < 0)
			return true;
		if(pos[1] >= cave[0].length || pos[1] < 0)
			return true;
		return cave[pos[0]][pos[1]] == null;
	}
	
	public boolean positionFree(int y, int x) {
		int[] temp = {y,x};
		return positionFree(temp);
	}
	
	public void setSand(int[] pos, Sand p) {
		cave[pos[0]][pos[1]] = p;
	}
	
	public boolean inCave(Sand p) {
		int[] pos = p.getPos();
		if(pos[0] < cave.length && pos[1] <cave[0].length && pos[1] >= 0 )
			return true;
		return false;
	}
	
}
