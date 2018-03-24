package com.clc.gpm.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * 
 * <p>ファイル名 : MessageUtil</p>
 * <p>説明 : Get message from properties file</p>
 * @author hung.pd
 * @since 2018/01/31
 */

@Component
public class MessageUtil {
    /** declare message reource*/
    @Autowired
    MessageSource messageSource;

    /** declare locale*/
    private Locale locale = Locale.getDefault();

    /**
     * Contructor none agrument
     */
    MessageUtil() {
    }

    /**
     * Message util Constructor
     * @param locale        Locale
     */
    MessageUtil(Locale locale) {
        this.locale = locale;
    }

    /** 
     * <p>説明 : getMessage</p> 
     * @author hung.pd
     * @since 2018/01/31
     * @param msgCode メッセージコード
     * @return メッセージ内容
     */
    public String getMessage(String msgCode) {
        return messageSource.getMessage(msgCode, null, locale);
    }

    /**
     * 
     * <p>説明 : getMessage</p> 
     * @author hung.pd
     * @since 2018/01/31
     * @param msgCode メッセージコード
     * @param agrs 
     * @return メッセージ内容
     */
    public String getMessage(String msgCode, String[] agrs) {
        return messageSource.getMessage(msgCode, agrs, locale);
    }
}