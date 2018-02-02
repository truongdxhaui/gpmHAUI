package com.clc.gpm.utils;

import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component("StringUtil")
public class StringUtil {
    /**
     * SPACE_HEX_IN_ASCII
     */
    private static final int SPACE_HEX_IN_ASCII = 0x20;

    //Convert an object to a string, and if it is null, convert it to the space
    public String convertNull(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    //Check object is null
    public boolean isNull(Object str) {
        return str == null || str.toString().length() <= 0;
    }

    //trim String
    public String trimStr(String str) {
        if (str != null) {
            return str.trim();
        }
        return "";
    }

    //isEmpty
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    //isNotEmpty
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }


     //isUppercaseAlpha

    public static boolean isUppercaseAlpha(char c) {
        return (c >= 'A') && (c <= 'Z');
    }

    //isLowercaseAlpha
    public static boolean isLowercaseAlpha(char c) {
        return (c >= 'a') && (c <= 'z');
    }

    //toUpperAscii
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
     * @return char
     */
    public static char toLowerAscii(char c) {
        if (isUppercaseAlpha(c)) {
            c += (char) SPACE_HEX_IN_ASCII;
        }
        return c;
    }

    /**
     *
     * <p>説明 : camelhumpToUnderline</p>
     * @author : [bp.thien.nv]
     * @since : [2017/12/26]
     * @param str String
     * @return String
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
     * @return Integer
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
     * @return Long
     */
    public static Long toLong(String str) {
        if (str != null && str.length() > 0) {
            return Long.valueOf(str);
        } else {
            return 0L;
        }
    }

    /**
     *
     * <p>説明 : String to BigInteger</p>
     * @author : [bp.thien.nv]
     * @since : [2017/12/21]
     * @param str String
     * @return BigInteger
     */
    public static BigInteger toBigInteger(String str) {
        if (str != null && str.length() > 0) {
            return new BigInteger(str);
        } else {
            return new BigInteger("0");
        }
    }
}
