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
}
