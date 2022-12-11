package defaultPackage;

import java.util.ArrayList;

public class Cpu {
	ArrayList<Integer> x;
	int[] xAtcycle;
	int cycle;
	public Cpu(String[] orders) {
		x = new ArrayList<Integer>();
		x.add(1);
		int xAkt = 1;
		for(String order : orders) {
			if(order.equals("noop")) {
				noop(xAkt);
			}else {
				String[] orderSplitted = order.split(" ");
				addx(Integer.parseInt(orderSplitted[1]),xAkt);
				xAkt += Integer.parseInt(orderSplitted[1]);
			}
		}
		
		xAtcycle = new int[x.size()];
		
		for(int i = 0; i< x.size();i++) {
			xAtcycle[i] = x.get(i);
		}
	 }
		
	public int[] getX() {
		int[] outPut = new int [x.size()];
		for(int i = 0; i< outPut.length;i++) {
			outPut[i] = x.get(i);
		}
		return outPut;
	}
	
	public void printCycles() {
		for(int i = 1; i< xAtcycle.length;i++){
			System.out.println(i+". Zyklus : " + xAtcycle[i-1]);
		}
	}
	
	private void noop(int xAkt) {
		x.add(xAkt);
	}
	
	private void addx(int p,int xAkt) {
		
		x.add(xAkt);
		x.add(xAkt+p);
	}
	
	public int getXAtCycle(int cycle) {
		return xAtcycle[cycle-1];
	}
	
	public int getSignalStrength(int cycle) {
		return cycle*getXAtCycle(cycle);
	}
}
