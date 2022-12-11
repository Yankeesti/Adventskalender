package defaultPackage;

import java.util.ArrayList;

public class Monkey {
	ArrayList<Integer> itemWorryLevel;
	int test; //devidable by this number
	int[] testThrow;//[0] the Monkey to throw to when test true [1] the monkey to throw to when test false
	String[] operation;
	int inspections;
	public Monkey(String[] data) {
		itemWorryLevel = new ArrayList<Integer>();
		String[] itemWorryLevleString = data[0].split(": ");
		itemWorryLevleString = itemWorryLevleString[1].split(", ");
		for(String p: itemWorryLevleString) {
			itemWorryLevel.add(Integer.parseInt(p));
		}
		
		operation = data[1].split(": ")[1].split(" ");
		
		test = Integer.parseInt(data[2].split("by ")[1]);
		
		testThrow = new int[2];
		
		testThrow[0] = Integer.parseInt(data[3].split("monkey ")[1]);
		testThrow[1] = Integer.parseInt(data[4].split("monkey ")[1]);
		inspections = 0;		
	}
	
	public void turn(Monkey[] monkeys) {
		for(int i = 0; i< itemWorryLevel.size();i++) {
			int item = itemWorryLevel.get(i);
			item = operation(item);
			if(item % test == 0) {
				monkeys[testThrow[0]].add(item);
			}else {
				monkeys[testThrow[1]].add(item);
			}
		}
		itemWorryLevel = new ArrayList<Integer>();
	}
	
	public void add(int item) {
		itemWorryLevel.add(item);
	}
	private int operation(int item) {
		int outPut = item;
		switch(operation[3]) {
		case "+":
			if(operation[4].equals("old"))
				outPut += item;
			else
				outPut += Integer.parseInt(operation[4]);
			break;
		case "*":
			if(operation[4].equals("old"))
				outPut *= item;
			else
				outPut *= Integer.parseInt(operation[4]);
			break;
		}
		outPut/=3;
		inspections++;
		return outPut;
	}
	
	public int[] getItems() {
		int[] outPut = new int[itemWorryLevel.size()];
		for(int i = 0; i<outPut.length;i++) {
			outPut[i] = itemWorryLevel.get(i);
		}
		return outPut;
	}
}
