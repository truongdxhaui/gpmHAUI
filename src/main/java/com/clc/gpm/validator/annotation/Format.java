package com.clc.gpm.validator.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({FIELD})
@Retention(RUNTIME)
public @interface Format {
    /**
     * N/A
     */
    public static final String N_A = "N/A";


    /**
     * FormatType
     * DATE
     * <p>説明 : ???</p>
     *
     * @author dts.bp34
     * @
     * @since : [2017/12/26]
     */
    enum FormatType {
        /**
         * DATE
         */
        DATE,
        /**
         * EMAIL
         */
        EMAIL,
        /**
         * PHONENUMBER
         */
        PHONENUMBER
    }


    FormatType type();

    String displayFieldName() default N_A;

    String pattern() default N_A;
}
