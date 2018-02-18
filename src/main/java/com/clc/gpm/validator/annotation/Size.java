package com.tau.validator.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>ファイル名 : Size</p>
 * <p>説明 : Size</p>
 * @author bp.truong.pq
 * @since 2017/11/25
 */
@Target({ FIELD })
@Retention(RUNTIME)
public @interface Size {
	int min() default 1;

	int max() default 1;

	String displayFieldName() default "N/A";

	String message() default "N/A";

}