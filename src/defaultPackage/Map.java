package defaultPackage;

import java.util.ArrayList;

public class Map {
	Position[][] positions;
	Position start,end;
	ArrayList<Position> posWithHeightA;
	public Map(String[] data) {
		positions = new Position[data.length][data[0].length()];
		posWithHeightA = new ArrayList<Position>();
		for(int y = 0;y<data.length;y++) {
			for(int x = 0; x < data[0].length();x++) {
				int[] kord = {y,x};
				positions[y][x] = new Position(kord, data[y].charAt(x));
				if(data[y].charAt(x) == 'S') {
					start = positions[y][x];
					posWithHeightA.add(positions[y][x]);}
				
				if(data[y].charAt(x) == 'E')
					end = positions[y][x];
				
				if(data[y].charAt(x) == 'a')
					posWithHeightA.add(positions[y][x]);
				
			}
		}
		
	}
	
	public Position findBestStartingPoint() {
		int steps = Integer.MAX_VALUE;
		Position startingPoint = null;
		for(int i = 0; i<posWithHeightA.size();i++) {
			posWithHeightA.get(i).setSteps(0);
			calculateWays(posWithHeightA.get(i));
			if(end.getSteps() < steps) {
				steps = end.getSteps();
				startingPoint = posWithHeightA.get(i);
			}
		}
		return startingPoint;
	}
	
	public int getStepsWithbestStartingPoint() {
		calculateWays(findBestStartingPoint());
		return end.getSteps();
	}
	
	public int getStepsTillEnd() {
		return end.getSteps();
	}
	
	
	public void calculateWays(Position start) {
		Position[] neighbours = getNeighbours(start);
		for(int i = 0; i< neighbours.length;i++) {
			if(neighbours[i].getSteps() > start.getSteps()+1) {
				neighbours[i].setPre(start);
				neighbours[i].setSteps(start.getSteps()+1);
				calculateWays(neighbours[i]);
			}
		}
	}

	/**
	 * 
	 * @param PosititionKord
	 * @return the travalable Neighbours(squares that are at most one higher) from the posiotion with kords PosititionKord
	 */
	private Position[] getNeighbours(Position p) {
		int[] posititionKord = p.kord;
		ArrayList<Position> outPutList = new ArrayList<Position>();
		//onder
		if(posititionKord[0]!=positions.length-1)
			if(positions[posititionKord[0]+1][posititionKord[1]].climbable(positions[posititionKord[0]][posititionKord[1]]))
			outPutList.add(positions[posititionKord[0]+1][posititionKord[1]]);
		//above
		if(posititionKord[0]!=0)
			if(positions[posititionKord[0]-1][posititionKord[1]].climbable(positions[posititionKord[0]][posititionKord[1]]))
			outPutList.add(positions[posititionKord[0]-1][posititionKord[1]]);
		
		//left
		if(posititionKord[1]!=0)
			if(positions[posititionKord[0]][posititionKord[1]-1].climbable(positions[posititionKord[0]][posititionKord[1]]))
					outPutList.add(positions[posititionKord[0]][posititionKord[1]-1]);
		
		//right
		if(posititionKord[1]!=positions[0].length-1)
			if(positions[posititionKord[0]][posititionKord[1]+1].climbable(positions[posititionKord[0]][posititionKord[1]]))
					outPutList.add(positions[posititionKord[0]][posititionKord[1]+1]);
		Position[] outPut = new Position[outPutList.size()];
		
		for(int i = 0; i< outPut.length;i++) {
			outPut[i] = outPutList.get(i);
		}
		return outPut;
	}
}
