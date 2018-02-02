package com.clc.gpm.validator.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>ファイル名 : HaftsizeNumber</p>
 * <p>説明 : HaftsizeNumber</p>
 * @author bp.truong.pq
 * @since 2017/11/25
 */
@Target({ FIELD })
@Retention(RUNTIME)
public @interface HaftsizeNumber {
	String displayFieldName() default "N/A";
}
