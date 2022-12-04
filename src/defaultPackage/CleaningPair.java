package defaultPackage;

public class CleaningPair {
	int[][] sections;
	public CleaningPair(String pair) {
		sections = new int[2][2];
		String[] section = pair.split(",");
		for(int i = 0; i<2;i++) {
			String[] temp = section[i].split("-");
			sections[i][0] = Integer.parseInt(temp[0]);
			sections[i][1] = Integer.parseInt(temp[1]);
		}
	}
	
	public boolean fullyContaines() {
		if(sections[0][0]<= sections[1][0] && sections[0][1]>= sections[1][1])
			return true;
		if(sections[1][0]<= sections[0][0] && sections[1][1]>= sections[0][1])
			return true;
		return false;

	}
	
	public boolean overLaping() {
		//second elf starts in the range of first elf
		if(	sections[0][0]<= sections[1][0] && 
			sections[0][1]>= sections[1][0])
			return true;
		//second eld ends in the range of first elf
		if(	sections[0][1]>= sections[1][1] && 
				sections[0][0]<= sections[1][1])
				return true;
		
		//second elf starts in the range of first elf
		if(	sections[1][0]<= sections[0][0] && 
			sections[1][1]>= sections[0][0])
			return true;
		//second eld ends in the range of first elf
		if(	sections[1][1]>= sections[0][1] && 
			sections[1][0]<= sections[0][1])
				return true;
		return false;
	}
}
