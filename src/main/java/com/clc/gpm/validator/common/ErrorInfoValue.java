package com.clc.gpm.validator.common;

import java.io.Serializable;

public class ErrorInfoValue implements Serializable {

    private static final long serialVersionUID = 5552835254119417468L;

    /**
     * item ID
     */
    private String errorId = null;

    /**
     * error message
     */
    private String errorMessage = null;

    /**
     * errorMessageを取得します。
     * 
     * @return errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * errorMessageを設定します。
     * 
     * @param errorMessage String
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * errorIdを取得します。
     * 
     * @return errorId
     */
    public String getErrorId() {
        return errorId;
    }

    /**
     * errorIdを設定します。
     * 
     * @param errorId String
     */
    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

}
