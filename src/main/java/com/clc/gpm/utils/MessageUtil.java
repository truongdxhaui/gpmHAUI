package com.tau.utils;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tau.exception.BaseException;
import com.tau.validator.common.BindingResult;
import com.tau.validator.common.ErrorInfoValue;

/**
 * <p>ファイル名 : MessageUtil</p>
 * <p>説明 : MessageUtil</p>
 * @author bp.truong.pq
 * @since 2017/11/25
 */
@Component("MessageUtil")
public class MessageUtil {

    /**
     * PropertiesUtilのインスタンス
     */
    @Autowired
    private PropertiesUtil propUtil;

    /**
     * get message list
     * 
     * @param errorList
     *            BindingResult
     * @param language
     * @return List<ErrorInfoValue>
     */
    public List<ErrorInfoValue> getMessageList(BindingResult errorList) {

        List<ErrorInfoValue> errors = new LinkedList<ErrorInfoValue>();
        if (errorList.getAllErrors().size() > 0) {
            Iterator<Throwable> ite = errorList.getAllErrors().iterator();
            Map<String, Integer> errorCheck = new HashMap<String, Integer>();
            Throwable ex = null;

            while (ite.hasNext()) {
                ex = ite.next();
                createErrorMessage(errors, ex, errorCheck);
            }
        }

        return errors;
    }

    /**
     * init error message
     * 
     * @param errors List<ErrorInfoValue>
     * @param e Throwable
     * @param errorCheck Map<String, Integer>
     * @param language
     */
    private void createErrorMessage(List<ErrorInfoValue> errors, Throwable e,
            Map<String, Integer> errorCheck) {

        Throwable ex = e;
        while (true) {
            if (!(ex instanceof InvocationTargetException)) {
                break;
            }
            if (ex == e.getCause()) {
                break;
            }
            if (e.getCause() != null) {
                ex = e.getCause();
            }
        }

        if (ex instanceof BaseException) {
            BaseException exc = (BaseException) ex;

            String fieldName = exc.getFieldName();
            String messageId = exc.getMessageId();
            String strTmp = messageId;
            String[] params = exc.getParam();

            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    if (params[i] != null) {
                        strTmp += params[i];
                    }
                }
            }

            ErrorInfoValue errorValue = new ErrorInfoValue();
            if (errorValue == null || errorValue.getErrorId() == null) {
                errorValue = new ErrorInfoValue();
            }
            // if (!errorCheck.containsKey(strTmp)) {
            errorCheck.put(strTmp, 0);
            errorValue.setErrorMessage(getMessage(messageId, params));
            // }

            errorValue.setErrorId(fieldName);
            errors.add(errorValue);
        }
    }

    /**
     * エラーメッセージが表示されます
     *
     * @param messageCode
     *            コードメッセージ
     * @param params
     *            メッセージにパラメータを置く
     * @param language
     * @return メッセージ
     */
    private String getMessage(String messageCode, String[] params) {
        params = processParams(params);
        String messageFormat = propUtil.getMsgProperty(messageCode);
        return getMessageNoCode(messageFormat, params);

    }

    /**
     * 処理パラメーター
     *
     * @param params
     *            メッセージにパラメータを置く
     * @return パラメータが処理
     */
    private String[] processParams(String[] params) {
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                if (params[i] == null) {
                    params[i] = "";
                }
            }
        }
        return params;
    }

    /**
     * エラーメッセージエラーコードを取得しない
     *
     * @param messageFormat
     *            メッセージはまだパラメータを入れていない
     * @param params
     *            メッセージにパラメータを置く
     * @return メッセージ
     */
    private String getMessageNoCode(String messageFormat, String[] params) {
        return new StringBuffer()
                .append(MessageFormat.format(messageFormat, (Object[]) params))
                .toString();
    }

}
