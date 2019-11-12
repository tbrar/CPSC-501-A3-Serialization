import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.Element;

public class Serializer {
	Element root;
	DocType type;
	Document doc;
	
	public Serializer() {
		root =  new Element("serialized");
		type = new DocType("Objects");
		doc = new Document(root, type);
	}
}
