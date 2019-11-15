import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
	
	public void serialize(Object obj) {
		if(!hashes.contains(String.valueOf(obj.hashCode()))){
			Element cl = new Element("object");
			root.addContent(0, cl);
			cl.setAttribute("class", obj.getClass().getName());
			cl.setAttribute("id", String.valueOf(obj.hashCode()));
			hashes.add(String.valueOf(obj.hashCode()));
			serializeFields(obj,cl);
		}
	}
	
	public void serializeFields(Object obj, Element cl) {
		Field[] fields = obj.getClass().getDeclaredFields();
		if(obj.getClass().isArray()) {
			serializeArray(obj, cl);
		}
		else if(obj instanceof Collection) {
			serializeCollection(obj, cl);
		}
		else {
		if(fields.length > 0) {
				for(Field field : fields) {
					field.setAccessible(true);
					Element fel = new Element("field");
					fel.setAttribute("name", field.getName());
					fel.setAttribute("declaringclass", field.getDeclaringClass().getName());
					cl.addContent(fel);
					if(field.getType().isPrimitive()) {
						serializePrimativeField(obj, field, fel);
					}
					else {
						serializeReferenceField(obj, field, fel);
					}
				}
			}
		}
	}

	private void serializeReferenceField(Object obj, Field field, Element fel) {
		Element ref = new Element("reference");
		fel.addContent(ref);
		try {
			ref.setText(String.valueOf(field.get(obj).hashCode()));
			serialize(field.get(obj));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void serializePrimativeField(Object obj, Field field, Element fel) {
		Element value = new Element("value");
		fel.addContent(value);
		try {
			value.setText(field.get(obj).toString());
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void serializeArray(Object obj, Element cl) {
		int length = Array.getLength(obj);
		cl.setAttribute("length", String.valueOf(length));
		for(int i = 0; i < length; i++) {
			if(obj.getClass().getComponentType().isPrimitive()) {
				Element value = new Element("value");
				cl.addContent(value);
				value.setText(Array.get(obj, i).toString());
			}
			else {
				Element ref = new Element("reference");
				cl.addContent(ref);
				ref.setText(String.valueOf(Array.get(obj, i).hashCode()));
				serialize(Array.get(obj, i));
			}
		}
	}

	private void serializeCollection(Object obj, Element cl) {
		Iterator it = ((Collection) obj).iterator();
		int i = 0;
		while(it.hasNext()) {
			Object objval = it.next();
			if(objval.getClass().isPrimitive()) {
				Element value = new Element("value");
				cl.addContent(value);
				value.setText(objval.toString());
			}
			else {
				Element ref = new Element("reference");
				cl.addContent(ref);
				ref.setText(String.valueOf(objval.hashCode()));
				serialize(objval);
			}
			i++;
		}
	}
	
	public Document getDocument() {
		return doc;
	}
}
