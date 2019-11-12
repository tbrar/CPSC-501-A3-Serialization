import java.io.IOException;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Sender to Receiver?");
		String selection = input.nextLine();
		Sender sender = null;
		if(selection.equals("Sender")) {
			try {
				sender = new Sender();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sender.start();
		}
		else if(selection.equals("Receiver")) {
			
		}
		else {
			System.out.println("That is not a valid option!");
		}
		return;
	}

}
