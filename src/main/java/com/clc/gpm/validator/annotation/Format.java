package com.clc.gpm.validator.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>ファイル名 : Format</p>
 * <p>説明 : Format</p>
 *
 * @author bp.truong.pq
 * @Target({ FIELD })  COMPACT_NO_ARRAY
 * @since 2017/11/25
 */
@Target({FIELD})
@Retention(RUNTIME)
public @interface Format {

    public static final String N_A = "N/A";

    enum FormatType {
        DATE,
        EMAIL,
        PHONENUMBER
    }


    FormatType type();


    String displayFieldName() default N_A;

    String pattern() default N_A;
}
