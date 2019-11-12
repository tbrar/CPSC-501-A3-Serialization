import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Sender {
	String addr = "127.0.0.1";
	int port = 49278;
	Creator creator = new Creator();
	Socket sock;
	ObjectOutputStream output;
	public Sender() throws IOException {
		sock = new Socket(addr, port);
		output = new ObjectOutputStream(sock.getOutputStream());
	}
	
	public void start() {
		switch(creator.displayMenu()) {
		case "1":
			creator.createA();
		}
	}


}
