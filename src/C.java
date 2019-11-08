
public class C {
	char[] array;
	
	public C(int length) {
		array = new char[length];
	}
	
	public void add(char letter, int index) {
		array[index] = letter;
		return;
	}
}
