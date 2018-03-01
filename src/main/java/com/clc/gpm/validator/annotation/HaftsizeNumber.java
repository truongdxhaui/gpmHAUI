package com.clc.gpm.validator.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * The interface Haftsize number.
 */
@Target({ FIELD })
@Retention(RUNTIME)
public @interface HaftsizeNumber {
    /**
     * Display field name string.
     *
     * @return the string
     */
    String displayFieldName() default "N/A";
}
