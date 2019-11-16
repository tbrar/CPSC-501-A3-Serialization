import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Receiver {
	int port = 49278;
	ServerSocket serverSocket;
	Socket socket;
	ObjectInputStream in;
	Deserializer deserializer;
	XMLOutputter xmlOut;
	FileOutputStream fos = null;
	 
	public Receiver(){
		deserializer = new Deserializer();
		xmlOut = new XMLOutputter(Format.getPrettyFormat());
		try {
			fos = new FileOutputStream("Receiver-Objects.xml");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void start() throws IOException {
		Document doc = null;
		serverSocket = new ServerSocket(port);
		System.out.println("Waiting for a connection.");
		socket = serverSocket.accept();
		in = new ObjectInputStream(socket.getInputStream());
		System.out.println("Connected.");
		try {
			doc = (Document) in.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		in.close();
		deserializer.deserialize(doc);
		xmlOut.output(doc,fos);
	}
}
