package com.clc.gpm.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * The interface Halftsize alphabet number.
 */
@Target({ FIELD })
@Retention(RUNTIME)
@Documented
public @interface HalftsizeAlphabetNumber {
    /**
     * Display field name string.
     *
     * @return the string
     */
    String displayFieldName() default "N/A";
}
