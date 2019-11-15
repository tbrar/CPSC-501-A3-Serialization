
public class D {
	C[] array;
	
	public D(){
		
	}
	
	public D(int length) {
		array = new C[length];
	}
	
	public void add(int index, C obj) {
		array[index] = obj;
		return;
	}
}
