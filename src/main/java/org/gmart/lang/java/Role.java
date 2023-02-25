package org.gmart.lang.java;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE_PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Role {
	String value();
}
