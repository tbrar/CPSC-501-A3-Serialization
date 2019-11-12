import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.Element;

public class Serializer {
	private Element root;
	private DocType type;
	private Document doc;
	private List<String> hashes;
	
	public Serializer() {
		root =  new Element("serialized");
		type = new DocType("Objects");
		doc = new Document(root, type);
		hashes = new ArrayList<String>();
	}
	
	public void serializeClass(Object obj) {
		if(!hashes.contains(String.valueOf(obj.hashCode()))){
			Element cl = new Element("object");
			root.addContent(cl);
			cl.setAttribute("class", obj.getClass().getSimpleName());
			cl.setAttribute("id", String.valueOf(obj.hashCode()));
			hashes.add(String.valueOf(obj.hashCode()));
			serializeFields(obj,cl);
		}
	}
	
	public void serializeFields(Object obj, Element cl) {
		Field[] fields = obj.getClass().getDeclaredFields();
		if(fields.length > 0) {
			for(Field field : fields) {
				field.setAccessible(true);
				Element fel = new Element("field");
				cl.addContent(fel);
				fel.setAttribute("name", field.getName());
				fel.setAttribute("declaringclass", field.getDeclaringClass().getSimpleName());
				if(field.getType().isPrimitive()) {
					Element value = new Element("value");
					fel.addContent(value);
					try {
						value.setText(field.get(obj).toString());
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					Element ref = new Element("reference");
					try {
						ref.setText(String.valueOf(field.get(obj).hashCode()));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public Document getDocument() {
		return doc;
	}
}
