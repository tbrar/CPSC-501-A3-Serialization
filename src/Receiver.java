import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.jdom2.Document;

public class Receiver {
	int port = 49278;
	ServerSocket serverSocket;
	Socket socket;
	ObjectInputStream in;
	 
	public Receiver(){
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
	}
}
