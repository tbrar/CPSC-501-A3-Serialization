import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;

public class Deserializer {
	Visualizer visualizer;
	List<Object> objects;
	HashMap<String, Object> hash;

	public Deserializer() {
		visualizer = new Visualizer();
		objects = new ArrayList<Object>();
		hash = new HashMap<String, Object>();
	}

	public void deserialize(Document doc) {
		Element root = doc.getRootElement();
		List<Element> elements = root.getChildren();
		for(int i = 0; i < elements.size(); i++ ){
			Element cl = elements.get(i);
			Class c = null;
			Constructor ctr = null;
			Object obj = null;
			try {
				c = Class.forName(cl.getAttributeValue("class"));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ctr = c.getDeclaredConstructor();
			} catch (NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				obj = ctr.newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Field[] fields = c.getDeclaredFields();
			for (int j = 0; j < fields.length; j++) {
				Field fd = fields[j];
				fd.setAccessible(true);
				String val = cl.getChildren().get(j).getChildren().get(0).getValue();
				if (fd.getType().equals(byte.class)) {
					try {
						fd.set(obj, Byte.valueOf(val));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(fd.getType().equals(short.class)) {
					try {
						fd.set(obj, Short.valueOf(val));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(fd.getType().equals(int.class)) {
					try {
						fd.set(obj, Integer.valueOf(val));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(fd.getType().equals(long.class)) {
					try {
						fd.set(obj, Long.valueOf(val));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(fd.getType().equals(float.class)) {
					try {
						fd.set(obj, Float.valueOf(val));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(fd.getType().equals(double.class)) {
					try {
						fd.set(obj, Double.valueOf(val));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(fd.getType().equals(char.class)) {
					try {
						fd.set(obj, Character.valueOf(val.charAt(0)));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(fd.getType().equals(boolean.class)) {
					try {
						fd.set(obj, Boolean.valueOf(val));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						}
					}
				else {
					Object objval = hash.get(val);
					try {
						fd.set(obj, objval);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			hash.put(String.valueOf(obj.hashCode()), obj);
			objects.add(obj);
		}
		
		for(int i = 0;i<objects.size();i++) {
			visualizer.inspect(objects.get(i), true);
		}
		
	}

	
}
