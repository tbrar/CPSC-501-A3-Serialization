import java.util.Scanner;

public class Creator {

	Scanner scanner = new Scanner(System.in);
	
	public String displayMenu() {
		System.out.println("Options:");
		System.out.println("1. A - object with primatives");
		System.out.println("2. B - object with reference A and C");
		System.out.println("3. C - object with array of primatives");
		System.out.println("4. D - object with array of As");
		System.out.println("5. E - object with collection of As");
		System.out.println("Which object would you like to make:");
		String input = scanner.nextLine();
		return input;
		
	}
	
	public A createA() {
		int par1;
		double par2;
		char par3;
		boolean par4;
		String input;
		System.out.println("A contains\n an int\n a double\n a char \n a boolean ");
		System.out.println("Enter the int:");
		input = scanner.nextLine();
		par1 = Integer.parseInt(input);
		System.out.println("Enter the double:");
		input = scanner.nextLine();
		par2 = Double.parseDouble(input);
		System.out.println("Enter the char:");
		input = scanner.nextLine();
		par3 = input.charAt(0);
		System.out.println("Enter the boolean(true or false):");
		input = scanner.nextLine();
		par4 = Boolean.parseBoolean(input);
		A obj = new A(par1, par2, par3, par4);
		return obj;
	}
	
	public B createB() {
		
		System.out.println("B contains\n an A object");
		A par1 = createA();
		B obj = new B(par1);
		return obj;
	}
	
	public C createC() {
		System.out.println("C contains\n a char array");
		int length = Integer.parseInt(scanner.nextLine());
		C obj = new C(length);
		for(int i = 0; i<length; i++){
			boolean valid = false;
			while(valid == false) {
				System.out.println("Enter a character:");
				String input = scanner.nextLine();
				if(input.length() == 1) {
					valid = true;
					obj.add(input.charAt(0), i);
				}
				else {
					System.out.println("That is not valid input!");
				}
			}
		}
		return obj;
	}
}
