package com.clc.gpm.utils;

import org.modelmapper.ModelMapper;

import java.lang.reflect.Field;
import java.util.List;


/**
 * The type Utilities.
 */
public class Utilities {

    /**
     * Gets system id.
     *
     * @return the system id
     */
    public static String getSystemId() {
        //TODO implement
        return "1";
    }

    /**
     * Trim object.
     *
     * @param targetObj the target obj
     * @return the object
     */
    public static Object trim(Object targetObj) {
        // get class
        Class<? extends Object> clazz = targetObj.getClass();
        // get lst fields of object
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // static field -> not process
            if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            // check if type string
            if (field.getType().equals(String.class)) {
                try {
                    field.setAccessible(true);
                    String fieldVal = StringUtil.convertNull(field.get(targetObj));
                    fieldVal = fieldVal.trim();
                    field.set(targetObj, fieldVal);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if(field.getType().equals(List.class)) {
            }
        }
        return targetObj;
    }


    /**
     * Map.
     *
     * @param source      the source
     * @param destination the destination
     */
    public static void map(Object source, Object destination) {
        if (source != null && destination != null) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setAmbiguityIgnored(true);
            modelMapper.map(source, destination);
        }
    }


    /**
     * Gets next element.
     *
     * @param mdCd the md cd
     * @param list the list
     * @return the next element
     */
    public static int getNextElement(int mdCd, List<Integer> list) {
        int idNext = -1;
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == mdCd) {
                    idNext = (i == (list.size() - 1)) ? -1 : list.get(i + 1);
                    break;
                }
            }
        }
        return idNext;
    }


    /**
     * Gets prev element.
     *
     * @param mdCd the md cd
     * @param list the list
     * @return the prev element
     */
    public static int getPrevElement(int mdCd, List<Integer> list) {
        int idPrev = -1;
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == mdCd) {
                    idPrev = (i == 0) ? -1 : list.get(i - 1);
                    break;
                }
            }
        }
        return idPrev;
    }

    /**
     * Compare str boolean.
     *
     * @param source the source
     * @param target the target
     * @return the boolean
     */
    public static boolean compareStr(String source, String target) {
        if (source == null) {
            return target == null;
        }
        return source.equals(target);
    }

    
}