package com.clc.gpm.validator.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p>ファイル名 : Fullsize</p>
 * <p>説明 : check fullsize character.</p>
 * @author bp.truong.pq
 * @since 2017/11/25
 */
@Target({ FIELD })
@Retention(RUNTIME)
public @interface Fullsize {
	String displayFieldName() default "N/A";

	String message() default "N/A";

}