import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
			serializer.serializeClass(creator.createA());
			break;
		case "2":
			serializer.serializeClass(creator.createB());
			break;
		case "3":
			serializer.serializeClass(creator.createC());
			break;
		case "5":
			serializer.serializeClass(creator.createE());
			break;
		case "exit":
			exit = true;
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
