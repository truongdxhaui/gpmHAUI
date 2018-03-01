package com.clc.gpm.utils;

import org.springframework.stereotype.Component;

import java.math.BigInteger;


/**
 * The type String util.
 */
@Component("StringUtil")
public class StringUtil {

    /**
     * SPACE_HEX_IN_ASCII
     */
    private static final int SPACE_HEX_IN_ASCII = 0x20;

    /**
     * Convert null string.
     *
     * @param obj the obj
     * @return the string
     */
    public String convertNull(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }


    /**
     * Is null boolean.
     *
     * @param str the str
     * @return the boolean
     */
    public boolean isNull(Object str) {
        return str == null || str.toString().length() <= 0;
    }

    /**
     * Trim str string.
     *
     * @param str the str
     * @return the string
     */
    public String trimStr(String str) {
        if (str != null) {
            return str.trim();
        }
        return "";
    }

    /**
     * isEmpty
     *
     * @param str String
     * @return boolean boolean
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * isNotEmpty
     *
     * @param str String
     * @return boolean boolean
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * isUppercaseAlpha
     *
     * @param c char
     * @return boolean boolean
     */
    public static boolean isUppercaseAlpha(char c) {
        return (c >= 'A') && (c <= 'Z');
    }

    /**
     * isLowercaseAlpha
     *
     * @param c char
     * @return boolean boolean
     */
    public static boolean isLowercaseAlpha(char c) {
        return (c >= 'a') && (c <= 'z');
    }

    /**
     * toUpperAscii
     *
     * @param c char
     * @return char char
     */
    public static char toUpperAscii(char c) {
        if (isLowercaseAlpha(c)) {
            c -= (char) SPACE_HEX_IN_ASCII;
        }
        return c;
    }

    /**
     * toLowerAscii
     *
     * @param c char
     * @return char char
     */
    public static char toLowerAscii(char c) {
        if (isUppercaseAlpha(c)) {
            c += (char) SPACE_HEX_IN_ASCII;
        }
        return c;
    }

    /**
     * <p>説明 : camelhumpToUnderline</p>
     *
     * @param str String
     * @return String string
     * @author : [bp.thien.nv]
     * @since : [2017/12/26]
     */
    public static String camelhumpToUnderline(String str) {
        final int size;
        final char[] chars;
        final StringBuilder sb = new StringBuilder(
                (size = (chars = str.toCharArray()).length) * 3 / 2 + 1);
        char c;
        for (int i = 0; i < size; i++) {
            c = chars[i];
            if (isUppercaseAlpha(c)) {
                sb.append('_').append(toLowerAscii(c));
            } else {
                sb.append(c);
            }
        }
        return sb.charAt(0) == '_' ? sb.substring(1) : sb.toString();
    }

    /**
     * String to int
     *
     * @param str String
     * @return Integer integer
     */
    public static Integer toInt(String str) {
        if (str != null && str.length() > 0) {
            return Integer.valueOf(str);
        } else {
            return 0;
        }
    }

    /**
     * String to long
     *
     * @param str String
     * @return Long long
     */
    public static Long toLong(String str) {
        if (str != null && str.length() > 0) {
            return Long.valueOf(str);
        } else {
            return 0L;
        }
    }

    /**
     * <p>説明 : String to BigInteger</p>
     *
     * @param str String
     * @return BigInteger big integer
     * @author : [bp.thien.nv]
     * @since : [2017/12/21]
     */
    public static BigInteger toBigInteger(String str) {
        if (str != null && str.length() > 0) {
            return new BigInteger(str);
        } else {
            return new BigInteger("0");
        }
    }
}
