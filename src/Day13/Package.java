package Day13;

public class Package {
	List left,right;
	public Package(String[] data) {
		left = new List(data[0], null, 0);
		right = new List(data[1], null, 0);
	}
	public int orderRight() {
		return List.orderdRight(left, right);
	}
}
