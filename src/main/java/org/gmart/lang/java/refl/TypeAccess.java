package org.gmart.lang.java.refl;

import java.util.stream.Stream;

import org.reflections.Reflections;

public class TypeAccess {

	public static <T> Stream<Class<? extends T>> getTypesExtending(String packageQualifiedName, Class<T> type){
		Reflections reflections = new Reflections(packageQualifiedName);
		return reflections.getSubTypesOf(type).stream();
	}

}
