package com.clc.gpm.validator.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>ファイル名 : HalftsizeAlphabetNumberSymbol</p>
 * <p>説明 : HalftsizeAlphabetNumberSymbol</p>
 * @author bp.truong.pq
 * @since 2017/11/25
 */
@Target({ FIELD })
@Retention(RUNTIME)
@Documented
public @interface HalftsizeAlphabetNumberSymbol {
	String displayFieldName() default "N/A";
}
