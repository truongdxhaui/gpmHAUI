package com.tau.validator.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>ファイル名 : Format</p>
 * <p>説明 : Format</p>
 * @author bp.truong.pq
 * @since 2017/11/25
 * @Target({ FIELD })  COMPACT_NO_ARRAY
 */
@Target({ FIELD })
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
     * @author dts.bp34
     * @since : [2017/12/26]
     * @
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

	/**
	 * <p>説明 : type</p> 
	 * @author : [dts.bp34]
	 * @since : [2017/12/26]
	 * @return FormatType
	 */
	FormatType type();

	/**
	 * <p>説明 : displayFieldName</p> 
	 * @author : [dts.bp34]
	 * @since : [2017/12/26]
	 * @return pattern
	 */
	String displayFieldName() default N_A;

	/**
	 * <p>説明 : pattern</p> 
	 * @author : [dts.bp34]
	 * @since : [2017/12/26]
	 * @return String
	 */
	String pattern() default N_A;
}
