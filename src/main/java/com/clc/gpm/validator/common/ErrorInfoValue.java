package com.clc.gpm.validator.common;

import java.io.Serializable;


/**
 * The type Error info value.
 */
public class ErrorInfoValue implements Serializable {

    private static final long serialVersionUID = 5552835254119417468L;


    private String errorId = null;


    private String errorMessage = null;


    /**
     * Gets error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }


    /**
     * Sets error message.
     *
     * @param errorMessage the error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    /**
     * Gets error id.
     *
     * @return the error id
     */
    public String getErrorId() {
        return errorId;
    }


    /**
     * Sets error id.
     *
     * @param errorId the error id
     */
    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

}
