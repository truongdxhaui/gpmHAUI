package com.clc.gpm.validator.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({ FIELD })
@Retention(RUNTIME)
public @interface Size {
	int min() default 1;

	int max() default 1;

	String displayFieldName() default "N/A";

	String message() default "N/A";

}