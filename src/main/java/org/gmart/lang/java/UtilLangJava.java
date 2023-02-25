package org.gmart.lang.java;

import java.util.stream.Stream;

public class UtilLangJava {
	public static boolean isIdentifier(String str) {
		return Character.isJavaIdentifierStart(str.charAt(0)) 
			&& Stream.of(str.substring(1).split("\\.")).allMatch(ch -> Character.isJavaIdentifierPart(ch.charAt(0)));
	}
}
