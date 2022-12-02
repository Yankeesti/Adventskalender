package defaultPackage;

public class Elf {
	private int calories;
	public Elf() {
		calories = 0;
	}
	
	public int getCalories() {
		return calories;
	}

	public void addCalories(int calories) {
		this.calories += calories;
		
	}
	
	public Elf clone() {
		Elf outPut = new Elf();
		outPut.addCalories(calories);
		return outPut;
	}
}
