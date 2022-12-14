package defaultPackage;
import java.util.*;
public class Stack {
		StringBuffer stack;
		public Stack(String pStack) {
			this.stack = new StringBuffer();
			this.stack.append(pStack);
			
		}
		
		public void move(int amount, Stack destination) {
			for(int i = 0;i<amount;i++) {
				char temp = stack.charAt(stack.length()-1);
				destination.add(temp);
				stack.delete(stack.length()-1, stack.length());
			}
		}
		
		public void add(char p) {
			stack.append(p);
		}
		
		public void move2(int amount, Stack destination) {
			for(int i = amount; i>0;i--) {
				char temp = stack.charAt(stack.length()-i);
				destination.add(temp);
			}
			stack.delete(stack.length()-amount, stack.length());
		}
		

		public char getTop() {
			return stack.charAt(stack.length()-1);
		}
}
