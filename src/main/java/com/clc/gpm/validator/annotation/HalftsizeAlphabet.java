package com.clc.gpm.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p>ファイル名 : HalftsizeAlphabet</p>
 * <p>説明 : HalftsizeAlphabet</p>
 * @author bp.truong.pq
 * @since 2017/11/25
 */
@Target({ FIELD })
@Retention(RUNTIME)
@Documented
public @interface HalftsizeAlphabet {
	String displayFieldName() default "N/A";
}
