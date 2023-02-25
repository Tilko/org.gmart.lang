package org.gmart.lang.java.refl;

public class TypeComparison {
	public static Class<?> getArgAssignableFromTheOtherArg_nullable(Class<?> class0, Class<?> class1) {
        if (class0.isAssignableFrom(class1)) {
            return class0;
        } else if (class1.isAssignableFrom(class1)) {
        	return class1;
        } else {
            return null;
        }
    }
}
