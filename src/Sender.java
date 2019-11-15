import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Sender {
	String addr = "127.0.0.1";
	int port = 49278;
	Creator creator = new Creator();
	Serializer serializer = new Serializer();
	Socket sock;
	ObjectOutputStream output;
	XMLOutputter xmlOut;
	FileOutputStream fos = null;
	public Sender() throws IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the IP address that you would like to connect to:");
		String addr = scanner.nextLine();
		sock = new Socket(addr, port);
		output = new ObjectOutputStream(sock.getOutputStream());
		xmlOut = new XMLOutputter(Format.getPrettyFormat());
		try {
			fos = new FileOutputStream("Sender-Objects.xml");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		boolean exit = false;
		while(!exit)
		switch(creator.displayMenu()) {
		case "1":
			serializer.serialize(creator.createA());
			break;
		case "2":
			serializer.serialize(creator.createB());
			break;
		case "3":
			serializer.serialize(creator.createC());
			break;
		case "4":
			serializer.serialize(creator.createD());
			break;
		case "5":
			serializer.serialize(creator.createE());
			break;
		case "6":
			exit = true;
			break;
		default:
			System.out.println("That is not a valid option.");
			break;
		}
		try {
			xmlOut.output(serializer.getDocument(),System.out);
			xmlOut.output(serializer.getDocument(),fos);
			output.writeObject(serializer.getDocument());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
