package com.clc.gpm.validator.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * The interface Max length.
 */
@Target({ FIELD })
@Retention(RUNTIME)
public @interface MaxLength {
    /**
     * Maxlength int.
     *
     * @return the int
     */
    int maxlength() default 1;

    /**
     * Display field name string.
     *
     * @return the string
     */
    String displayFieldName() default "N/A";
}
