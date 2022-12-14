package defaultPackage;

import java.util.ArrayList;

import Day13.List;
import Day13.Package;
import Day14.Rock;
import Day14.Sand;
import Day14.Structure;

public class TestBench {

	public static void main(String[] args) {
		Structure s = new Sand(0, 0);
		Structure r = new Rock(0, 0);
		
		if(s instanceof Sand) {
			System.out.print("Sand");
		}
		
		if(r instanceof Sand) {
			System.out.print("Sand");
		}
	
	}

}
