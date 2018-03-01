package com.clc.gpm.validator.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * The interface Size.
 */
@Target({ FIELD })
@Retention(RUNTIME)
public @interface Size {
    /**
     * Min int.
     *
     * @return the int
     */
    int min() default 1;

    /**
     * Max int.
     *
     * @return the int
     */
    int max() default 1;

    /**
     * Display field name string.
     *
     * @return the string
     */
    String displayFieldName() default "N/A";

    /**
     * Message string.
     *
     * @return the string
     */
    String message() default "N/A";

}