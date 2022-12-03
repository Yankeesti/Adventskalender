package defaultPackage;

public class Rucksack {
	String[] compartments;
	public Rucksack(String items) {
		compartments = new String[2];
		StringBuffer itemList = new StringBuffer();
		for(int i = 0; i< items.length()/2;i++) {
			itemList.append(items.charAt(i));
		}
		compartments[0] = itemList.toString();
		itemList = new StringBuffer();
		for(int i = items.length()/2; i< items.length();i++) {
			itemList.append(items.charAt(i));
		}
		compartments[1] = itemList.toString();
	}
	
	/**
	 * 	finds the item that apears in both compartments and outPuts it's Value
	 * @return Lowercase item types a through z have priorities 1 through 26 and Uppercase item types A through Z have priorities 27 through 52.
	 */
	public int findDouble() {
		char doubleItem = '-';
		end:
		for(int iOne = 0; iOne < compartments[0].length();iOne++) {
			for(int iTwo = 0; iTwo < compartments[1].length();iTwo++) {
				if(compartments[0].charAt(iOne) == (compartments[1].charAt(iTwo))) {
					doubleItem = compartments[0].charAt(iOne);
					break end;
				}
			}
		}
		if(doubleItem == '-')
			return -1;
		return getValue(doubleItem);
	}
	
	protected int getValue(char p) {
		if((int) p <= 90 && (int) p >= 65) {//uperCase
			return  (int) p -65 +27;
		}else if((int) p <= 122 && (int) p >= 97) {//lower Case
			return (int) p -96;
		}
		return -1;
	}
	
	public String getData() {
		StringBuffer outPut = new StringBuffer();
		outPut.append(compartments[0]);
		outPut.append(compartments[1]);
		return outPut.toString();
	}
}
