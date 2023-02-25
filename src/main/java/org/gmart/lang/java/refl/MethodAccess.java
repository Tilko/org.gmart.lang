package org.gmart.lang.java.refl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

public class MethodAccess {

	// public static Stream<Method> getMethods(Class<?> classOrInterface, boolean notWithModifiers, int
	// ORed_Modifier) {
	// Predicate<? super Method> predicate = notWithModifiers ? m ->
	// MathBitwise.noBitInCommon(m.getModifiers(), ORed_Modifier)
	// : m -> MathBitwise.allBitInSecondExistInFirst(m.getModifiers(), ORed_Modifier);
	// return Stream.of(classOrInterface.getMethods()).filter(predicate);
	// }
	// public static Stream<Method> getDeclaredMethods(Class<?> classOrInterface, Predicate<Method>
	// filtering) {
	// return filterMethods_internal(classOrInterface.getDeclaredMethods(), filtering);
	// }
	// public static Stream<Method> getMethods(Class<?> classOrInterface, BiPredicate<Integer,Integer>
	// filtering_firstArgIsMethod_secondIsParam, int ORed_Modifier) {
	// return filterMethods_internal(classOrInterface.getMethods(),
	// filtering_firstArgIsMethod_secondIsParam, ORed_Modifier);
	// }
	public static Stream<Method> filterMethods(Method[] methods, BiPredicate<Integer, Integer> filtering_firstArgIsMethod_secondIsParam,
			int ORed_Modifier) {
		return Stream.of(methods).filter(m -> filtering_firstArgIsMethod_secondIsParam.test(m.getModifiers(), ORed_Modifier));
	}

	// public static Stream<Method> filterMethods(Method[] methods, Predicate<Method> filtering) {
	// return Stream.of(methods).filter(filtering);
	// }
	public static Stream<Method> getMethodsWithAnnotation(Class<?> jClass, Class<? extends Annotation> annoType) {
		return Stream.of(jClass.getDeclaredMethods()).filter(method -> {
			return Stream.of(method.getDeclaredAnnotations()).filter(anno -> anno.annotationType().equals(annoType)).findFirst()
					.isPresent();
		});
	}

	public static Stream<Method> getMethodsWithAnnotation(Method method) {
		Stream.of(method.getParameters());
		return null;
	}

}
