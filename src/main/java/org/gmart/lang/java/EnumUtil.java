package org.gmart.lang.java;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;


public class EnumUtil {
	public static List<String> getNames(Class<? extends Enum<?>> e) {
	    return Arrays.stream(e.getEnumConstants()).map(Enum::name).toList();//Array(String[]::new);
	}
	

	public static <E extends Enum<E>> Stream<E> getEnumConstants(Class<E> e) {
	    return Arrays.stream(e.getEnumConstants());
	}
	public static <E extends Enum<E>> Stream<E> getEnumConstantsWithName(Class<E> cl, Predicate<String> nameFilter) {
	    return getEnumConstants(cl).filter(e -> nameFilter.test(e.name()));
	}
	public static <E extends Enum<E>> Stream<E> getEnumConstantsWithPrefix(Class<E> cl, String prefix) {
	    return getEnumConstantsWithName(cl, name -> name.startsWith(prefix));
	}
	public static <E extends Enum<E>> Stream<E> getEnumConstantsWithPrefixAndRemaining(Class<E> cl, String prefix, Set<String> remainings) {
		int prefixLen = prefix.length();
	    return getEnumConstantsWithName(cl, name -> {
	    		if(!name.startsWith(prefix)) 
	    			return false;
	    		return remainings.contains(name.substring(prefixLen));
	    	});
	}
}
