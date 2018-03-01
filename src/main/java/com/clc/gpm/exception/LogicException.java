package com.clc.gpm.exception;

/**
 * The type Logic exception.
 */
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
     * Instantiates a new Logic exception.
     *
     * @param messageId the message id
     * @param param     the param
     */
    public LogicException(String messageId, String[] param) {
        this.messageId = messageId;
        this.setParam(param);
    }

    /**
     * Instantiates a new Logic exception.
     *
     * @param messageId the message id
     */
    public LogicException(String messageId) {
        this.messageId = messageId;
    }

    /**
     * Gets message id.
     *
     * @return the message id
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * Sets message id.
     *
     * @param messageId the message id
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * Get param string [ ].
     *
     * @return the string [ ]
     */
    public String[] getParam() {
        return param;
    }

    /**
     * Sets param.
     *
     * @param param the param
     */
    public void setParam(String[] param) {
        this.param = param;
    }

}
