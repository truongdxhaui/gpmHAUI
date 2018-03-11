package com.clc.gpm.utils;

import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.apache.commons.validator.EmailValidator;


/**
 * The type Check util.
 */
@Component("CheckUtil")
public class CheckUtil {

    private static final String[] halfWidthEnglish = { "A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e",
            "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
            "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4",
            "5", "6", "7", "8", "9" };

    private static final String[] halfAlphabetNumberSymbol = { "A", "B", "C",
            "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
            "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c",
            "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
            "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2",
            "3", "4", "5", "6", "7", "8", "9", "!", "@", "#", "$", "%", "^",
            "&", "*", "(", ")", "-", "_", "=", "+", ":", ";", "'", "\"", ",",
            "<", "/", "?", "`", "~" };

    /**
     * Input valid : Katakana/Numbers/Letters
     *
     * @param input String
     * @return boolean boolean
     */
    public boolean isFullSize(String input) {
        if (input == null && !"".equals(input)) {
            return true;
        }
        Pattern p = Pattern.compile("^[０-９,Ａ-ｚ]+$");
        Matcher m = p.matcher(input);

        return m.find();
    }

    /**
     * Check HaftSizeAlphabet
     *
     * @param input String
     * @return boolean boolean
     */
    public boolean isHaftSizeAlphabet(String input) {
        if (input == null && !"".equals(input)) {
            return true;
        }
        Pattern p = Pattern.compile("^[A-Z, ]+$");
        Matcher m = p.matcher(input);

        return m.find();
    }

    /**
     * Is haftsize numeric boolean.
     *
     * @param input String
     * @return 数値は 、trueの場合はnullまたは空白である
     */
    public boolean isHaftsizeNumeric(String input) {
        if (input == null && !"".equals(input)) {
            return true;
        }
        Pattern p = Pattern.compile("^[0-9]+$");
        Matcher m = p.matcher(input);

        return m.find();
    }

    /**
     * Check string is haft size alphabet and is haft size number
     *
     * @param input String
     * @return true if string is haft size engligh
     */
    public boolean isHalfSizeAlphabetAndNumber(String input) {
        if (input == null && !"".equals(input)) {
            return true;
        }

        Pattern p = Pattern.compile("^[A-Z,1-9]+$");
        Matcher m = p.matcher(input);

        return m.find();
    }

    /**
     * Check HalfSizeAlphabetAndNumber
     *
     * @param val String
     * @return boolean boolean
     */
    public boolean isHalfSizeAlphabetAndNumber2(String val) {
        if (isNull(val)) {
            return false;
        }
        char[] part = val.toCharArray();
        boolean isEnglish = false;
        for (char c : part) {
            isEnglish = false;
            for (String str : halfWidthEnglish) {
                if (str.equals(String.valueOf(c))) {
                    isEnglish = true;
                    break;
                }
            }
            if (!isEnglish) {
                return false;
            }
        }
        return true;
    }

    /**
     * check HalfAlphabet or Number or Symbol
     *
     * @param input String
     * @return boolean boolean
     */
    public boolean isHalfAlphabetNumberSymbol(String input) {
        if (isNull(input)) {
            return false;
        }
        char[] part = input.toCharArray();
        boolean isHalfAlphabetNumberSymbol = false;
        for (char c : part) {
            isHalfAlphabetNumberSymbol = false;
            for (String str : halfAlphabetNumberSymbol) {
                if (str.equals(String.valueOf(c))) {
                    isHalfAlphabetNumberSymbol = true;
                    break;
                }
            }
            if (!isHalfAlphabetNumberSymbol) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check format of date string.
     *
     * @param dateString   the date string
     * @param formatString the format string
     * @return boolean  true : string is valid date format false : string is invalid date format
     */
    public boolean isDateFormat(String dateString, String formatString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
        try {
            LocalDate.parse(dateString, formatter);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    /**
     * Validate email address.
     *
     * @param email the email
     * @return boolean boolean
     */
    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\"
                + ".[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * Check String input is number
     *
     * @param number String
     * @return true : String input is number. false: String input is not number
     */
    public boolean isSignNumber(String number) {
        if (isNull(number)) {
            return true;
        }

        Pattern p = Pattern.compile("^[-]?[0-9]+$");
        Matcher m = p.matcher(number);

        return m.find();
    }

    /**
     * Check String is null
     *
     * @param str String
     * @return true String input is null or length = 0
     */
    public boolean isNull(String str) {
        return (str == null || str.length() <= 0);
    }

    /**
     * Check char input is number
     *
     * @param c char
     * @return true : char is number. false: char not number
     */
    public boolean isDigit(char c) {
        int x = (int) c;
        if ((x >= 48 && x <= 57) || x == 45) {
            return true;
        }
        return false;
    }
}
