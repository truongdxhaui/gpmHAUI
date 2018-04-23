package com.clc.gpm.utils;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The type String util.
 */
public class StringUtil {

    /** SPACE_HEX_IN_ASCII */
    private static final int SPACE_HEX_IN_ASCII = 0x20;

    /**
     * Convert null string.
     *
     * @param obj the obj
     * @return the string
     */
    public static String convertNull(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    /**
     * Is null object boolean.
     *
     * @param str the str
     * @return the boolean
     */
    public static boolean isNullObject(Object str) {
        return str == null || str.toString().length() <= 0;
    }

    /**
     * Is null boolean.
     *
     * @param str the str
     * @return the boolean
     */
    public static boolean isNull(String str) {
        return str == null || str.trim().length() <= 0;
    }

    /**
     * Chg null string.
     *
     * @param str the str
     * @return the string
     */
    public static String chgNull(String str) {
        if (str == null || "".equals(str.trim())) {
            return "";
        }
        return str.trim();
    }

    /**
     * Trim str string.
     *
     * @param str the str
     * @return the string
     */
    public static String trimStr(String str) {
        if (str != null) {
            return str.trim();
        }
        return "";
    }

    /**
     * Is empty boolean.
     *
     * @param str the str
     * @return the boolean
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * Is not empty boolean.
     *
     * @param str the str
     * @return the boolean
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * Is uppercase alpha boolean.
     *
     * @param c the c
     * @return the boolean
     */
    public static boolean isUppercaseAlpha(char c) {
        return (c >= 'A') && (c <= 'Z');
    }

    /**
     * Is lowercase alpha boolean.
     *
     * @param c the c
     * @return the boolean
     */
    public static boolean isLowercaseAlpha(char c) {
        return (c >= 'a') && (c <= 'z');
    }

    /**
     * To upper ascii char.
     *
     * @param c the c
     * @return the char
     */
    public static char toUpperAscii(char c) {
        if (isLowercaseAlpha(c)) {
            c -= (char) SPACE_HEX_IN_ASCII;
        }
        return c;
    }

    /**
     * To lower ascii char.
     *
     * @param c the c
     * @return the char
     */
    public static char toLowerAscii(char c) {
        if (isUppercaseAlpha(c)) {
            c += (char) SPACE_HEX_IN_ASCII;
        }
        return c;
    }

    /**
     * Camelhump to underline string.
     *
     * @param str the str
     * @return the string
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
     * To int integer.
     *
     * @param str the str
     * @return the integer
     */
    public static Integer toInt(String str) {
        if (str != null && str.length() > 0) {
            return Integer.valueOf(str);
        } else {
            return 0;
        }
    }

    /**
     * To long long.
     *
     * @param str the str
     * @return the long
     */
    public static Long toLong(String str) {
        if (str != null && str.length() > 0) {
            return Long.valueOf(str);
        } else {
            return 0L;
        }
    }

    /**
     * To big integer big integer.
     *
     * @param str the str
     * @return the big integer
     */
    public static BigInteger toBigInteger(String str) {
        if (str != null && str.length() > 0) {
            return new BigInteger(str);
        } else {
            return new BigInteger("0");
        }
    }

    /**
     * To str date format string.
     *
     * @param date   the date
     * @param format the format
     * @return the string
     */
    public static String toStrDateFormat(LocalDateTime date, String format) {
        if (date == null || isNull(format)) {
            return null;
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return date.format(formatter);
        }
    }
}