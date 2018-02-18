package com.tau.utils;

import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Component;

/**
 * <p>ファイル名 : PropertiesUtil</p>
 * <p>説明 : PropertiesUtil</p>
 * @author bp.truong.pq
 * @since 2017/11/25
 */
@Component("PropertiesUtil")
public class PropertiesUtil {

    /**
     * messages.properties.xml
     */
    public static final String MESSAGE_PROP = "messages.properties.xml";

    /**
     * items
     */
    public static final String ITEMS_PROP = "items";

    /**
     * messageProp
     */
    private Properties messageProp;

    /**
     * ロード·メッセージ
     */
    public PropertiesUtil() {
        if (messageProp == null) {
            messageProp = this.getProperties(MESSAGE_PROP);
        }
    }

    /**
     * 
     * <p>説明 : getMsgProperty</p> 
     * @author : [bp.thien.nv]
     * @since : [2017/12/26]
     * @param key String
     * @return String
     */
    public String getMsgProperty(String key) {
        return messageProp.getProperty(key);
    }

    /**
     * XMLプロパティを取得
     * 
     * @param propertiesFileName String
     * @return Propertiesオブジェクト
     */
    public Properties getProperties(String propertiesFileName) {

        Properties prop = new Properties();

        InputStream istream = null;

        try {
            istream = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(propertiesFileName);
            prop.loadFromXML(istream);

            return prop;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            if (istream != null) {
                try {
                    istream.close();
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }
        }
    }

    /**
     * messagePropを取得します。
     * 
     * @return messageProp
     */
    public Properties getMessageProp() {
        return messageProp;
    }

    /**
     * messagePropを設定します。
     *  
     * @param messageProp Properties
     */
    public void setMessageProp(Properties messageProp) {
        this.messageProp = messageProp;
    }

}
