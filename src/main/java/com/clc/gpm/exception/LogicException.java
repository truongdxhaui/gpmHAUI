package com.clc.gpm.exception;

public class LogicException extends Exception {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * messageId
     */
    private String messageId = null;

    /**
     * param
     */
    private String[] param;

    /**
     * LogicException
     * @param messageId String
     * @param param String[]
     */
    public LogicException(String messageId, String[] param) {
        this.messageId = messageId;
        this.setParam(param);
    }

    /**
     * LogicException
     * @param messageId String
     */
    public LogicException(String messageId) {
        this.messageId = messageId;
    }

    /**
     * get messageId
     * @return the messageId
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * set messageId
     * @param messageId the messageId to set
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * get param
     * @return the param
     */
    public String[] getParam() {
        return param;
    }

    /**
     * set param
     * @param param the param to set
     */
    public void setParam(String[] param) {
        this.param = param;
    }

}
