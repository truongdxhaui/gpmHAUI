package com.clc.gpm.validator.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * The interface Format.
 */
@Target({FIELD})
@Retention(RUNTIME)
public @interface Format {
    /**
     * N/A
     */
    public static final String N_A = "N/A";

    /**
     * The enum Format type.
     */
    enum FormatType {
        /**
         * DATE
         */
        DATE,
    }


    /**
     * Type format type.
     *
     * @return the format type
     */
    FormatType type();

    /**
     * Display field name string.
     *
     * @return the string
     */
    String displayFieldName() default N_A;

    /**
     * Pattern string.
     *
     * @return the string
     */
    String pattern() default N_A;
}
