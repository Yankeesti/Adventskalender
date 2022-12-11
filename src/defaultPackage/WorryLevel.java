package defaultPackage;

public class WorryLevel {
	private int[] rest; //rest of worry level devided by index +1
	public WorryLevel(int worryLevel) {
		rest = new int [50];
		for(int i = 0 ; i<rest.length ; i++) {
			rest[i] = worryLevel%(i+1);
		}
	}
	
	public void multiply(int multiplier) {
		for(int i = 0 ; i<rest.length ; i++) {
			rest[i] = (rest[i]*multiplier)%(i+1);
		}
	}
	
	public void addition(int p) {
		for(int i = 0 ; i<rest.length ; i++) {
			rest[i] = (rest[i]+p)%(i+1);
		}
	}
	
	public void square() {
		for(int i = 0 ; i<rest.length ; i++) {
			rest[i] = (rest[i]*rest[i])%(i+1);
		}
		
	}
	
	public boolean devisbleBy(int dividor) {
		return rest[dividor-1] == 0;
	}
}
