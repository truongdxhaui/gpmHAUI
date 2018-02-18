package com.tau.validator.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>ファイル名 : HalftsizeAlphabetNumber</p>
 * <p>説明 : HalftsizeAlphabetNumber</p>
 * @author bp.truong.pq
 * @since 2017/11/25
 */
@Target({ FIELD })
@Retention(RUNTIME)
@Documented
public @interface HalftsizeAlphabetNumber {
	String displayFieldName() default "N/A";
}
