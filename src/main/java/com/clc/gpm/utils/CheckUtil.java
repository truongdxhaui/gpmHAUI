package com.clc.gpm.utils;

import com.clc.gpm.common.CommonConstants;
import org.apache.commons.lang3.StringUtils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>ファイル名 : CheckUtil</p>
 * <p>説明 : CheckUtil</p>
 *
 * @author bp.truong.pq
 * @since 2017/11/25
 */
public class CheckUtil {
    /**
     * Declare halfWidthEnglish
     */
    private static final String[] halfWidthEnglish = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x",
            "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    /**
     * Declare halfAlphabetNumberSymbol
     */
    private static final String[] halfAlphabetNumberSymbol = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
            "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
            "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "=", "+", ":",
            ";", "'", "\"", ",", "<", "/", "?", "`", "~"};

    /**
     * Input valid : Katakana/Numbers/Letters
     *
     * @param input String
     * @return boolean
     */
    public static boolean isFullSize(String input) {
        if (StringUtil.isNull(input)) {
            return true;
        }
        Pattern p = Pattern.compile("^[ァ-ン,０-９,Ａ-ｚ]+$");
        Matcher m = p.matcher(input);

        return m.find();
    }

    /**
     * Check HaftSizeAlphabet
     *
     * @param input String
     * @return boolean
     */
    public static boolean isHaftSizeAlphabet(String input) {
        if (StringUtil.isNull(input)) {
            return true;
        }
        Pattern p = Pattern.compile("^[A-Z,a-z, ]+$");
        Matcher m = p.matcher(input);

        return m.find();
    }

    /**
     * チェック文字列が数値である
     *
     * @param input String
     * @return 数値は、trueの場合はnullまたは空白である
     */
    public static boolean isHaftsizeNumeric(String input) {
        if (StringUtil.isNull(input)) {
            return true;
        }
        Pattern p = Pattern.compile("^[0-9]+$");
        Matcher m = p.matcher(input);
        return m.matches();
    }

    /**
     * Check string is haft size alphabet and is haft size number
     *
     * @param input String
     * @return true if string is haft size engligh
     */
    public static boolean isHalfSizeAlphabetAndNumber(String input) {
        if (StringUtil.isNull(input)) {
            return true;
        }

        Pattern p = Pattern.compile("^[A-Z,a-z,1-9]+$");
        Matcher m = p.matcher(input);

        return m.find();
    }

    /**
     * Check HalfSizeAlphabetAndNumber
     *
     * @param val String
     * @return boolean
     */
    public static boolean isHalfSizeAlphabetAndNumber2(String val) {
        if (StringUtil.isNull(val)) {
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
     * @return boolean
     */
    public static boolean isHalfAlphabetNumberSymbol(String input) {
        if (StringUtil.isNull(input)) {
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
     * @param dateString   Date string value
     * @param formatString Format date
     * @return boolean
     * true : string is valid date format
     * false : string is invalid date format
     */
    public static boolean isDateFormat(String dateString, String formatString) {
        if (StringUtil.isNull(dateString)) {
            return false;
        }
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
     * @param email Eメール
     * @return Eメールチェック結果
     */
    public static boolean isValidEmailAddress(String email) {
        if (StringUtil.isNull(email)) {
            return true;
        }
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\"
                + ".[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * FIXME　Check String input is number
     *
     * @param number FIXME　String
     * @return true: FIXME　String input is number. false: String input is not number
     */
    public static boolean isSignNumber(String number) {
        if (StringUtil.isNull(number)) {
            return true;
        }

        Pattern p = Pattern.compile("^[-]?[0-9]+$");
        Matcher m = p.matcher(number);

        return m.find();
    }

    /**
     * FIXME Check char input is number
     *
     * @param c char
     * @return true: char is number. false: char not number
     */
    public static boolean isDigit(char c) {
        int x = (int) c;
        if ((x >= 48 && x <= 57) || x == 45) {
            return true;
        }
        return false;
    }

    /**
     * <p>説明 : FIXME Check giá nhập có phải integer không</p>
     *
     * @param input value String
     * @return true : FIXME nếu giá trị nhập vào là số nguyên
     * @author : truong.pq
     * @since : 2018/03/07
     */
    public static boolean isIntegerNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    /**
     * <p>説明 : FIXME Check SQL injection</p>
     *
     * @param value script sql
     * @return True if error sql injection
     * @author hung.pd
     * @since 2018/02/07
     */
    public static boolean isSQLInjection(String value) {
        boolean isValid = false;
        if (StringUtils.isEmpty(value)) {
            return isValid;
        }
        String[] keyInject = {"create", "insert", "grant", "delete", "drop", "truncate", "select", ";", CommonConstants.SPACE_HAFTSIZE};
        for (String string : keyInject) {
            String lowerCase = value.toLowerCase();
            if (lowerCase.indexOf(string + CommonConstants.SPACE_HAFTSIZE) >= 0) {
                isValid = true;
            }
        }
        return isValid;
    }

    /*public static <T> boolean checkDuplicateData(List<T> lstObject) {
        if (CollectionUtils.isEmpty(lstObject)) {
            return false;
        }

        Set<T> set = new TreeSet<T>(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                if (o1 instanceof BaseDTO) {
                    Field[] fields = o1.getClass().getDeclaredFields();
                    int iDuplicate = 0;
                    for (Field field : fields) {
                        try {
                            if (field.isAnnotationPresent(HeaderCsv.class)) {
                                Object invoke1 = new PropertyDescriptor(field.getName(), o1.getClass()).getReadMethod().invoke(o1);
                                Object invoke2 = new PropertyDescriptor(field.getName(), o2.getClass()).getReadMethod().invoke(o2);
                                if (invoke1.equals(invoke2)) {
                                    iDuplicate += 0;
                                } else {
                                    iDuplicate += 1;
                                }
                            }

                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IntrospectionException e) {
                            e.printStackTrace();
                        }
                    }
                    iDuplicate = (iDuplicate == 0) ? 0 : 1;
                    return iDuplicate;
                } else if (o1 instanceof Integer || o1 instanceof String) {
                    if (o1.equals(o2)) {
                        return 0;
                    } else {
                        return 1;
                    }
                } else {
                    return 1;
                }
            }
        });
        set.addAll(lstObject);
        final ArrayList<T> newList = new ArrayList<T>(set);
        return newList.size() < lstObject.size();
    }

    public static <T> boolean checkDuplicateFieldswithAnotation(List<T> lstObject) {
        if (CollectionUtils.isEmpty(lstObject)) {
            return false;
        }
        Set<T> set = new TreeSet<T>(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                if (o1 instanceof BaseDTO || o1 instanceof BaseFormatDTO) {
                    Field[] tempFields = o1.getClass().getDeclaredFields();
                    int iDuplicate = 0;
                    for (Field field : tempFields) {
                        try {
                            if (field.isAnnotationPresent(UniqueKey.class)) {
                                Object invoke1 = new PropertyDescriptor(field.getName(), o1.getClass()).getReadMethod().invoke(o1);
                                Object invoke2 = new PropertyDescriptor(field.getName(), o2.getClass()).getReadMethod().invoke(o2);
                                if (invoke1.equals(invoke2)) {
                                    iDuplicate += 0;
                                    if (o1 instanceof BaseFormatDTO) {
                                        Map<String, MessageCSV> messages = new HashMap<>();
                                        MessageCSV messageCSV = new MessageCSV(field.getAnnotation(UniqueKey.class).message());
                                        messages.put(field.getName(), messageCSV);
                                        ((BaseFormatDTO) o1).setIsValid(false);
                                        ((BaseFormatDTO) o1).setMessages(messages);
                                        ((BaseFormatDTO) o2).setIsValid(false);
                                        ((BaseFormatDTO) o2).setMessages(messages);
                                    }
                                } else {
                                    iDuplicate += 1;
                                }
                            }

                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IntrospectionException e) {
                            e.printStackTrace();
                        }
                    }
                    iDuplicate = (iDuplicate == 0) ? 0 : 1;
                    return iDuplicate;
                } else if (o1 instanceof Integer || o1 instanceof String) {
                    if (o1.equals(o2)) {
                        return 0;
                    } else {
                        return 1;
                    }
                } else {
                    return 1;
                }
            }
        });
        set.addAll(lstObject);
        final ArrayList<T> newList = new ArrayList<T>(set);
        return newList.size() < lstObject.size();
    }

    public static boolean checkMultipartFile(MultipartFile file, String fileName, FileExtension fileExtention) {

        // ファイルのサイズをチェック
        if (file == null || file.getSize() == 0) {
            return false;
        }

        // ファイル名をチェック
        if (!StringUtils.isEmpty(fileName) && !file.getOriginalFilename().equals(fileName)) {
            return false;
        }

        // ファイルextentionsをチェック
        List<FileExtension> fileExtensions = Arrays.asList(FileExtension.values());
        if (!fileExtensions.contains(fileExtention)) {
            return false;
        }
        return true;
    }*/
}
