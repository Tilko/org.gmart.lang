package org.gmart.lang.java.refl;

import org.apache.commons.io.FilenameUtils;

public class StackInfoAccess {
	public static String getCallerClassSimpleName() {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		return FilenameUtils.removeExtension(stackTrace[2].getFileName());
	}
	public static void main(String[] args) {
		System.out.println(getCallerFullyQualifiedTypeName(0));
	}
	public static Class<?> getCallerClass(int stackDepthOffset) {
		try {
			return Class.forName(getCallerFullyQualifiedTypeName(stackDepthOffset+1));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String getCallerFullyQualifiedTypeName(int stackDepthOffset) {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		String className = stackTrace[2 + stackDepthOffset].getClassName();
//		Stream.of(stackTrace).forEach(st -> System.out.println(st));
//		System.out.println("stackDepthOffset:" + stackDepthOffset);
//		System.out.println("className:" + className);
//		System.out.println("removeExtension:" + removeExtension);
		return className;
	}
	public static String getCallerPackageName(int stackDepthOffset) {
		String removeExtension = FilenameUtils.removeExtension(getCallerFullyQualifiedTypeName(stackDepthOffset+1));
//		System.out.println("removeExtension:" + removeExtension);
		return removeExtension;
	}
//	public static String getCallerClassPackageName(int depthOffset) {
//		return getCallerClassPackageName(depthOffset + 1);//FilenameUtils.removeExtension(getCallerClassFullName(1));
//	}
	public static String getMainClassSimpleName() {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		return FilenameUtils.removeExtension(stackTrace[stackTrace.length-1].getFileName());
	}
}
