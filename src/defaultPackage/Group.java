package defaultPackage;

public class Group extends Rucksack{
	Rucksack[] rucksaecke;
	public Group(String[] rucksaecke) {
		super(rucksaecke[0]);
		this.rucksaecke = new Rucksack[2];
		this.rucksaecke[0] = new Rucksack(rucksaecke[1]);
		this.rucksaecke[1] = new Rucksack(rucksaecke[2]);
	}
	
	public int findBatch() {
		String[] data = new String[3];
		data[0] = getData();
		data[1] = rucksaecke[0].getData();
		data[2] = rucksaecke[1].getData();
		
		for(int iOne = 0; iOne <data[0].length();iOne++) {
			for(int iTwo = 0; iTwo <data[0].length();iTwo++) {
				if(data[0].charAt(iOne) == data[1].charAt(iTwo))
				for(int iThree = 0; iThree <data[0].length();iThree++) {
					if(data[1].charAt(iTwo) == data[2].charAt(iThree)) {
						return getValue(data[1].charAt(iTwo));
					}
				}
			}
		}
		return -1;
	}
}
