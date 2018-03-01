package com.clc.gpm.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * The interface Ignore.
 */
@Target({ FIELD })
@Retention(RUNTIME)
@Documented
public @interface Ignore {

}
