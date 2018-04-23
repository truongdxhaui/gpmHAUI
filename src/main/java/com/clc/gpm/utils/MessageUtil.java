package com.clc.gpm.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * The type Message util.
 */
@Component
public class MessageUtil {
    /**
     * declare message reource
     */
    @Autowired
    MessageSource messageSource;

    /** declare locale*/
    private Locale locale = Locale.getDefault();


    /**
     * Instantiates a new Message util.
     */
    MessageUtil() {
    }


    /**
     * Instantiates a new Message util.
     *
     * @param locale the locale
     */
    MessageUtil(Locale locale) {
        this.locale = locale;
    }


    /**
     * Gets message.
     *
     * @param msgCode the msg code
     * @return the message
     */
    public String getMessage(String msgCode) {
        return messageSource.getMessage(msgCode, null, locale);
    }


    /**
     * Gets message.
     *
     * @param msgCode the msg code
     * @param agrs    the agrs
     * @return the message
     */
    public String getMessage(String msgCode, String[] agrs) {
        return messageSource.getMessage(msgCode, agrs, locale);
    }
}