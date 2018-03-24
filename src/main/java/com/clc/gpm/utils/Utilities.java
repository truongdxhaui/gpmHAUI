package com.clc.gpm.utils;

import org.modelmapper.ModelMapper;

import java.lang.reflect.Field;
import java.util.List;

/**
 * <p>ファイル名 : Utilities.java</p>
 * <p>説明 : Utilities.java</p>
 * @author minh.ls
 * @since 2018/03/03
 */
public class Utilities {
/**
 * 
 * <p>説明 : FIXME get system id </p> 
 * @author : phong.nd
 * @since : 2018/02/22
 * @return String
 */
    public static String getSystemId() {
        //TODO implement
        return "1";
    }
    /**
     * 
     * <p>説明 : FIXME Mô tả ý nghia method</p> 
     * @author : duc.bv
     * @since : 2018/02/27
     * @param targetObj
     * @return FIXME Mô nghĩa param/return
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
                //TODO
            }
        }
        return targetObj;
    }

    /**
     * <p>説明 : map data</p> 
     * @author : minh.ls
     * @since : 2018/03/03
     * @param source Object
     * @param destination Object
     */
    public static void map(Object source, Object destination) {
        if (source != null && destination != null) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setAmbiguityIgnored(true);
            modelMapper.map(source, destination);
        }
    }
    
    /**
    *
    * <p>説明 : getNextElement</p>
    * @author : thien.nv
    * @since 2017/12/25
    * @param mdCd Integer
    * @param list List<Integer>
    * @return Integer
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
    *
    * <p>説明 : getPrevElement</p>
    * @author : minh.nt
    * @since  : 2017/12/27
    * @param mdCd Integer
    * @param list List<Integer>
    * @return Integer
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
     * 
     * <p>説明 : FIXME Kiem tra 2 string co giong nhau hay khong</p> 
     * @author : duc.bv
     * @since : 2018/03/04
     * @param source String
     * @param target String
     * @return boolean result
     */
    public static boolean compareStr(String source, String target) {
        if (source == null) {
            return target == null;
        }
        return source.equals(target);
    }

    
}