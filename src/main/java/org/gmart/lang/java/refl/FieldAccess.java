package org.gmart.lang.java.refl;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.stream.Stream;

public class FieldAccess {
//	public static boolean setPrivateFinalField(Class<?> clazz, String fieldName, Object instance, Object value) {
//		try {
//			Field field = clazz.getDeclaredField(fieldName);
//			setPrivateFinalField(clazz, field, instance, value);
//			return true;
//		} catch (NoSuchFieldException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
	private static final VarHandle MODIFIERS;

	  static {
	    try {
	      var lookup = MethodHandles.privateLookupIn(Field.class, MethodHandles.lookup());
	      MODIFIERS = lookup.findVarHandle(Field.class, "modifiers", int.class);
	    } catch (IllegalAccessException | NoSuchFieldException ex) {
	      throw new RuntimeException(ex);
	    }
	  }
	public static boolean setPrivateFinalField(Field field, Object instance, Object value) {
		try {
			field.setAccessible(true);
			MODIFIERS.set(field, field.getModifiers() & ~Modifier.FINAL);
			field.setAccessible(true);
			field.set(instance, value);
			return true;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static  class Main {

		  

		  public static void main(String[] args) throws Exception {
		    Field emptyElementDataField = ArrayList.class.getDeclaredField("EMPTY_ELEMENTDATA");
		    // make field non-final
		    MODIFIERS.set(emptyElementDataField, emptyElementDataField.getModifiers() & ~Modifier.FINAL);
		    
		    // set field to new value
		    emptyElementDataField.setAccessible(true);
		    emptyElementDataField.set(null, new Object[] {"Hello", "World!"});

		    var list = new ArrayList<>(0);

		    // println uses toString(), and ArrayList.toString() indirectly relies on 'size'
		    var sizeField = ArrayList.class.getDeclaredField("size");
		    sizeField.setAccessible(true);
		    sizeField.set(list, 2); // the new "empty element data" has a length of 2

		    System.out.println(list);
		  }
		}
	
	public static Stream<Field> getAllFields(Class<?> clazz){
		Class<?> superclass = clazz.getSuperclass();
		Stream<Field> inheritedFields = superclass != null ? getAllFields(superclass) : Stream.empty();
		return Stream.concat(inheritedFields, Stream.of(clazz.getDeclaredFields()));
	}
	
	public static Object getDeepFieldValue(Object context, String[] path) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		if(path.length == 0) {
			return context;
		} else {
			return FieldAccess.getDeepFieldValue(context, path, 0);
		}
	}
	static Object getDeepFieldValue(Object context, String[] path, int index) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = context.getClass().getField(path[index]);
		field.setAccessible(true);
		Object newContext = field.get(context);
		if(index != path.length - 1) {
			return getDeepFieldValue(newContext, path, index + 1);
		} else {
			return newContext;
		}
	}
}
