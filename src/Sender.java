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
	public Sender() throws IOException {
		//sock = new Socket(addr, port);
		//output = new ObjectOutputStream(sock.getOutputStream());
		xmlOut = new XMLOutputter(Format.getPrettyFormat());
	}
	
	public void start() {
		switch(creator.displayMenu()) {
		case "1":
			serializer.serializeClass(creator.createA());
		}
		try {
			xmlOut.output(serializer.getDocument(),System.out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
