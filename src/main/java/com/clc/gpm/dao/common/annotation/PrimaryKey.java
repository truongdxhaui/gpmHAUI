package com.clc.gpm.dao.common.annotation;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The interface Primary key.
 */
@Retention(RUNTIME)
public @interface PrimaryKey {

	/**
	 * Name string.
	 *
	 * @return the string
	 */
	String name() default "N/A";
}
