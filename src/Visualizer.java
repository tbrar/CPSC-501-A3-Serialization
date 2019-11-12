import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Visualizer {
    public void inspect(Object obj, boolean recursive) {
        Class c = obj.getClass();
        inspectClass(c, obj, recursive, 0, false);
    }

    private void inspectClass(Class c, Object obj, boolean recursive, int depth, boolean fieldarrayprinted) {
    	System.out.println();
    	
    	indent(depth);
    	
		System.out.println("#####START CLASS INSPECTION#####");
		
    	indent(depth);
    	printClassName(c);
    	System.out.println();
    	
		if(c.isArray() && fieldarrayprinted == false) {
	    	indent(depth);
			printArrayInfo(c,obj,recursive,depth, fieldarrayprinted);
			System.out.println();
		}
    	
    	indent(depth);
		printFields(c, obj, recursive, depth, fieldarrayprinted);
    	System.out.println();
		
    	indent(depth);
		System.out.println("####END CLASS INSPECTION####");
    }
    
    public void printArrayInfo(Class c, Object obj, boolean recursive, int depth, boolean fieldarrayprinted) {
    	System.out.println("COMPONENT TYPE: " + c.getComponentType());
    	indent(depth);
    	System.out.println("ARRAY LENGTH: " + Array.getLength(obj));
    	indent(depth);
    	System.out.print("ARRAY CONTENTS: ");
		for(int j = 0; j < Array.getLength(obj); j++) {
			System.out.print(Array.get(obj, j) + " ");
		}
		System.out.println();
		for(int j = 0; j < Array.getLength(obj); j++) {
			if(recursive == true && Array.get(obj, j) != null && !c.getComponentType().isPrimitive()) {
		    	indent(depth);
		    	System.out.print("ELEMENT " + j + ":");
				inspectClass(Array.get(obj, j).getClass(), Array.get(obj, j), recursive, depth+1, fieldarrayprinted);
			}
		}
    }

	public void printSuperClasses(Class c, Object obj, boolean recursive, int depth, boolean fieldarrayprinted) {
		System.out.println("----START SUPERCLASSES----");
    	Class temp = c;
    	while(temp.getSuperclass() != null) {
    		temp = temp.getSuperclass();
    		inspectClass(temp, obj, recursive, depth+1, fieldarrayprinted);
    	}
    	
    	indent(depth);
    	
		System.out.println("----END SUPERCLASSES----");
	}

	public void printInterfaces(Class c, Object obj, boolean recursive, int depth, boolean fieldarrayprinted) {
		System.out.println("----START INTERFACES----");
    	Class<?>[] interfaces = c.getInterfaces();
    	for(int i = 0; i < interfaces.length; i++) {
    		inspectClass(interfaces[i], obj, recursive, depth+1, fieldarrayprinted);
    	}
    	
    	indent(depth);
    	
		System.out.println("----END INTERFACES----");
	}

	public void printFields(Class c, Object obj, boolean recursive, int depth, boolean fieldarrayprinted) {
		boolean printed = false;
		System.out.println("----START FIELDS----");
    	Field[] fields = c.getDeclaredFields();
    	for(int i = 0; i < fields.length; i++) {
        	indent(depth);
        	
    		System.out.println("FIELD " + i + ": " + fields[i].getName());
    		
        	indent(depth);
        	
    		System.out.println("TYPE: " + fields[i].getType());
    		
        	indent(depth);
    		
    		System.out.println("MODIFERS: " + Arrays.asList(Modifier.toString(fields[i].getModifiers())));
    		
        	indent(depth);
        	
    		if(fields[i].getType().isArray()) {
    			printed = true;
				try {
					fields[i].setAccessible(true);
		        	System.out.println("COMPONENT TYPE: " + fields[i].get(obj).getClass().getComponentType());
		        	indent(depth);
		        	System.out.println("ARRAY LENGTH: " + Array.getLength(fields[i].get(obj)));
		        	indent(depth);
		        	System.out.print("ARRAY CONTENTS: ");
					for(int j = 0; j < Array.getLength(fields[i].get(obj)); j++) {
						System.out.print(Array.get(fields[i].get(obj), j) + " ");
					}
					System.out.println();
					for(int j = 0; j < Array.getLength(fields[i].get(obj)); j++) {
						if(recursive == true && Array.get(fields[i].get(obj), j) != null && !fields[i].get(obj).getClass().getComponentType().isPrimitive()) {
							indent(depth);
					    	System.out.print("ELEMENT " + j + ":");
							inspectClass(Array.get(fields[i].get(obj), j).getClass(), Array.get(fields[i].get(obj), j), recursive, depth+1, fieldarrayprinted);
						}
					}
		    		
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
        	
			try {
				fields[i].setAccessible(true);
				Object field = fields[i].get(obj);
	    		if(field != null) {
					System.out.println("VALUE: " + field);
	    		
					if(recursive == true && !(fields[i].getType().isPrimitive())) {
						inspectClass(fields[i].getType(), field, recursive, depth+1, printed);
					}
	    		}
			} catch (IllegalArgumentException | IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

    	}
    	indent(depth);
		System.out.println("----END FIELDS----");
	}

	public void printMethods(Class c, int depth) {
		System.out.println("----START METHODS----");
    	Method[] meths = c.getDeclaredMethods();
    	for(int i = 0; i < meths.length; i++) {
    		
        	indent(depth);
        	
    		System.out.println("METHOD " + i + ": " + meths[i].getName());
    		
        	indent(depth);
        	
    		System.out.println("EXCEPTION TYPES: " + Arrays.asList(meths[i].getExceptionTypes()));
    		
        	indent(depth);
        	
    		System.out.println("PARAMETER TYPES: " + Arrays.asList(meths[i].getParameterTypes()));
    		
        	indent(depth);
        	
    		System.out.println("RETURN TYPE: " + Arrays.asList(meths[i].getReturnType()));
    		
    		indent(depth);
        	
    		System.out.println("MODIFERS: " + Arrays.asList(Modifier.toString(meths[i].getModifiers())));
    	}
    	
    	indent(depth);
    	
		System.out.println("----END METHODS----");
	}

	public void printConstructors(Class c, int depth) {
		System.out.println("----START CONSTRUCTORS----");
    	Constructor<?>[] cons = c.getConstructors();
    	for(int i = 0; i < cons.length; i++) {
    		
        	indent(depth);
        	
    		System.out.println("CONSTRUCTOR " + i + ": " + cons[i].getName());
    		
        	indent(depth);
        	
        	Class<?>[] types = cons[i].getParameterTypes();
        	System.out.println("TYPES: " + Arrays.asList(types));
        	
        	indent(depth);
        	
        	int mods = cons[i].getModifiers();
        	System.out.println("MODIFIERS: " + Modifier.toString(mods));
    	}
    	indent(depth);
    	System.out.println("----END CONSTRUCTORS----");
	}

	public void printClassName(Class c) {
		if(c.isInterface() == true) {
			System.out.println("INTERFACE NAME: " + c.getName());
    	}
		else if(c.isArray() == true) {
			System.out.println("ARRAY NAME: " + c.getName());
		}
    	else {
    		System.out.println("CLASS NAME: " + c.getName());
    	}
	}

	public void indent(int depth) {
		for(int i = 0; i < depth; i++) {
    		System.out.print('\t');
    	}
	}
	

}
