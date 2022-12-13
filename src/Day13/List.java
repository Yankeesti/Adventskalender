package Day13;

import java.util.ArrayList;

public class List extends ArrayList<Object>{
	List parentList;
	int thisSublist;
	public List(String data , List parentList, int psubList) {
		this.parentList  = parentList;
		thisSublist = psubList;
		int listIndex = 0;
		if(data.charAt(0) == '[') {
			//eliminate first and last Char of data
			StringBuffer temp = new StringBuffer();
			temp.append(data);
			temp.delete(temp.length()-1, temp.length());
			temp.delete(0, 1);
			data = temp.toString();
		}
		
		while(data.length() != 0) {
			if(data.charAt(0) == '[') {
				String[] temp = getData(data,0);
				add(new List(temp[0],this,size()-1));
				data = temp[1];
			}else if(data.charAt(0) == ']');
			else {
				data = addNumber(data);
			}
		}
	}
	
	/**
	 * 
	 * @param left
	 * @param right
	 * @return 1 when left is in the right order -1 when not and 0 if not decideble 2 when both sides run out of items
	 */
	public static int orderdRight(List left,List right){
	
		for(int i = 0; i< left.size();i++) {
			if(i+1 > right.size() && i+1 <= left.size())
				return -1;
			if(left.get(i).getClass().toString().equals("class Day13.List") && right.get(i).getClass().toString().equals("class Day13.List")) {
				int erg = orderdRight((List)left.get(i),(List) right.get(i));
				if(erg == 1)
					return 1;
				if(erg == -1)
					return -1;
				if(erg == 2) {
					if(i+1 >= left.size() && i+1 < right.size())
						return 1;
					continue;}
			}else if(left.get(i).getClass().toString().equals("class Day13.List") || right.get(i).getClass().toString().equals("class Day13.List")) {//Compare List to int
				if(left.get(i).getClass().toString().equals("class Day13.List"))// right is int
					right.toList(i);
				else if(right.get(i).getClass().toString().equals("class Day13.List"))// left is int
					left.toList(i);
				int erg = orderdRight((List)left.get(i),(List) right.get(i));
				if(erg == 1)
					return 1;
				if(erg == -1)
					return -1;
			}else {//compare int to int
				if(((int)left.get(i)) < ((int)right.get(i)))//left is smaler
					return 1;
				if(((int)left.get(i)) > ((int)right.get(i)))//left is biger
					return -1;
				
				if(i+1 >= left.size() && i+1 < right.size())//left runs out of items
					return 1;
			}
			
		}
		if(left.size() == 0 && right.size()>0)
			return 1;
		if(left.size() == 0 && right.size() == 0)
			return 2;
		return 0;
	}
	
	private void toList(int index) {
		int data = (int) get(index);
		
		List newData = new List("["+data+"]", this, 0);
		
		set(index, newData);
	}
	
	/**
	 * adds the number to the arrayList
	 * @return new data String withou Number
	 */
	private String addNumber(String data) {
		String[] dataSplitted = data.split(",");
		add(Integer.parseInt(dataSplitted[0]));
		
		return delete(data, 0, dataSplitted[0].length()+1);
		
		
	}
	
	/**
	 * 	finds the data that belongs to the list starting at charIndexBegin
	 * @param startString - main string
	 * @param charIndexBegin - start of List data
	 * @return [0] = data that belongs to the list starting at charIndexBegin [1] start without [0]
	 */
	private static String[] getData(String startString,int charIndexBegin) {
		int opened = 0;
		int closed = 0;
		int aktChar = charIndexBegin;
		
		do {
			if(startString.charAt(aktChar) == '[')
				opened ++;
			else if(startString.charAt(aktChar) == ']')
				closed++;
			aktChar++;
		}while(opened != closed);
		
		StringBuffer dataOne = new StringBuffer();
		
		for(int i = charIndexBegin; i<aktChar;i++) {
			dataOne.append(startString.charAt(i));
		}
		
		StringBuffer dataTwo = new StringBuffer();
		dataTwo.append(startString);
		dataTwo.delete(charIndexBegin, aktChar+1);
		
		String[] outPutArr = {dataOne.toString(),dataTwo.toString()};
		return outPutArr;
	}
	
	
	/**
	 * 
	 * @param p
	 * @param start
	 * @param end
	 * @return String where all parts from start to (excluding end) are deleted
	 */
	private String delete(String p,int start,int end) {
		StringBuffer outPut = new StringBuffer();
		outPut.append(p);
		outPut.delete(start, end);
		return outPut.toString();
	}
}
