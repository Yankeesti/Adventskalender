package Day15;

import java.util.ArrayList;
import java.util.Arrays;

import Day14.Rock;
import Day14.Sand;

public class Cave {
	ArrayList<Sensor> sensors;
	ArrayList<Beacon> beacons;
	public Cave() {
		sensors = new ArrayList<Sensor>();
		beacons = new ArrayList<Beacon>();
	}
	
	public void addSenosor(Sensor p) {
		//p.setData(this);
		sensors.add(p);
		beacons.add(p.getBeacon());
	}
	
	public int getBeaconexcluded(int line) {
		int[] blocked = new int[0];
		
		for(int i = 0; i< sensors.size();i++) {
			Arrays.sort(blocked);
			blocked = uniteArrays(blocked, sensors.get(i).getBlockedPositions(line));
		}
		
		for(int i = 0; i< beacons.size();i++) {
			//delete beacons on this line
			if(beacons.get(i).getPos()[1] == line) {
				blocked = delete(blocked,beacons.get(i).getPos()[0]);
			}
				
		}
		
		return blocked.length;
	}
	
	/**
	 * 
	 * @param line
	 * @param min
	 * @param max
	 * @return max value if no beacon in this line is not possible else the x position
	 */
	public int BeaconPossible(int line,int min, int max) {
		ArrayList<int[]> blocked = new ArrayList<int[]>();
		for(int i = 0; i< sensors.size();i++) {
			int[] temp = sensors.get(i).getBlockedPositions(line,min,max);
			if(temp.length == 2) {
				blocked.add(temp);
				blocked = uniteBlocked(blocked);
				}
			if(blocked.size() == 1) {
				if(blocked.get(0)[0] == min && blocked.get(0)[1] == max)
					return Integer.MAX_VALUE;
			}
		}
		if(blocked.size() == 1) {
			return Integer.MAX_VALUE;
		}
		if(blocked.get(0)[1] < blocked.get(1)[1])
			return blocked.get(0)[1]+1;
		return blocked.get(1)[1]+1;
	}
	
	private ArrayList<int[]> uniteBlocked(ArrayList<int[]> a){
		top:
		for(int i = 0; i< a.size();i++) {
			for(int i2 = i+1; i2<a.size();i2++) {
				if(samePoint(a.get(i), a.get(i2))) {
				int[] temp = uniteBlocked(a.get(i), a.get(i2))[0];
				a.add(temp);
				a.remove(i2);
				a.remove(i);
				i2--;
				i--;
				continue top;
				}
			}
		}
			
		return a;
	}
	
	private int[][] uniteBlocked(int[] a, int[] b) {
		//Array with lower start at Beginning
		if(a[0] > b[0])
			return uniteBlocked(b, a);
		
		if((a[0] <= b[0] && b[0] <= a[1])|| a[1]+1 == b[0]) {// b starts in a 
			if(b[1] <= a[1]) {// b is completely in a 
				return new int[][]{{a[0],a[1]}};
			}else {// b starts in a but ends after a
				return new int[][]{{a[0],b[1]}};
			}
		}else {// b and a have no ovelappint blocked
			return new int[][]{a,b};
		}
		
		
	}
	
	private boolean samePoint(int[] a, int[] b) {
		//Array with lower start at Beginning
			if(a[0] > b[0])
				return samePoint(b, a);
		return (a[0] <= b[0] && b[0] <= a[1])|| a[1]+1 == b[0];
	}
	
	private int[] removeDuplicateElements(int[] arr){ 
		if(arr.length > 0) {
	       Arrays.sort(arr);
	       ArrayList<Integer> outPutList = new ArrayList<Integer>();
	       outPutList.add(arr[0]);
	       for(int i = 0; i< arr.length; i++) {
	    	   if(arr[i] != outPutList.get(outPutList.size()-1))
	    		   outPutList.add(arr[i]);
	       }
	       int [] outPut = new int[outPutList.size()];
	       for(int i = 0; i< outPut.length;i++) {
	    	   outPut[i] = outPutList.get(i);
	       }
	       return outPut;}
		return arr;
	    }  
	
	private  int[] delete(int[] arr, int p) {
		int[] outPut = new int[arr.length-1];
		boolean pFound = false;
		int indexOutPut = 0;
		for(int i = 0; i< arr.length;i++) {
			if(arr[i] == p) {
				pFound = true;
			}else {
				if(indexOutPut < outPut.length)
				outPut[indexOutPut] = arr[i];
				indexOutPut++;
			}
		}
		
		if(pFound)
			return outPut;
		else
			return arr;
	}
	
	private int[] uniteArrays(int[] a, int[] b) {
		int[] outPut = new int[a.length+b.length];
		int outPutIndex = 0;
		for(int i = 0; i<a.length;i++) {
			outPut[outPutIndex] = a[i];
			outPutIndex ++;
		}
		for(int i = 0; i<b.length;i++) {
			outPut[outPutIndex] = b[i];
			outPutIndex ++;
		}
		outPut = removeDuplicateElements(outPut);
		return outPut;
	}
	
	public long getTuningFrequency(int min,int max) {
		for(int line = min; line<=max; line++) {
			int erg = BeaconPossible(line, min, max);
			if(erg != Integer.MAX_VALUE) {
				
				return (long)((long)(erg)*4000000+line);
			}
		}
		return 0;
	}
}
