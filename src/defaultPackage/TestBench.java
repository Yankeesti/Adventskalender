package defaultPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import Day13.List;
import Day13.Package;
import Day14.Rock;
import Day14.Sand;
import Day14.Structure;
import Day15.Sensor;

public class TestBench {
	public static void main(String[] args) {
		Sensor test = new Sensor(8, 7, 2, 10);
		for(int i = -2; i<16;i++) {
		int[] temp = test.getBlockedPositions(i, 0, 20);
		
		System.out.println(temp[0]+" | "+temp[1]);
		}
	}
	
	
	
	private static int[] delete(int[] arr, int p) {
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
	
	private static int[] uniteArrays(int[] a, int[] b) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(int i = 0;i<a.length;i++) {
			temp.add(a[i]);
		}
		for(int i = 0;i<b.length;i++) {
			temp.add(b[i]);
		}
		temp.sort(null);
		
		for(int i = 0;i<temp.size();i++) {
				while(i+1<temp.size()) {
					if(temp.get(i)== temp.get(i+1)) 
						temp.remove(i+1);
						else
							break;
				}
		}
		int[] outPut = new int[temp.size()] ;
		for(int i = 0; i<outPut.length;i++) {
			outPut[i] = temp.get(i);
		}
		return outPut;
	}
	
	public static int[] removeDuplicateElements(int[] arr){  
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
       return outPut;
    }  
	

}
