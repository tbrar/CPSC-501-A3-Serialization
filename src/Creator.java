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
		System.out.println("A contains\n an int\n a double\n a char \n a boolean ");
		String input = scanner.nextLine();
		System.out.println("Enter the int:");
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
}
