package com.clc.gpm.exception;


/**
 * The type Base exception.
 */
public class BaseException extends Exception {

    private static final long serialVersionUID = -3130750938679514286L;


    private String messsageId = null;

    private String fieldName = null;


    private String defaultMessage = null;

    private String[] param = null;

    private boolean logOutput = false;

    /**
     * Instantiates a new Base exception.
     *
     * @param e the e
     */
    public BaseException(Throwable e) {
        super(e);
    }


    /**
     * Instantiates a new Base exception.
     *
     * @param code    the code
     * @param param   the param
     * @param errorId the error id
     * @param e       the e
     */
    public BaseException(String code, String[] param, String errorId,
            Throwable e) {
        super(e);
        this.messsageId = code;
        this.param = param;
        this.fieldName = errorId;
    }

    /**
     * Instantiates a new Base exception.
     *
     * @param code    the code
     * @param param   the param
     * @param errorId the error id
     */
    public BaseException(String code, String[] param, String errorId) {
        this.messsageId = code;
        this.param = param;
        this.fieldName = errorId;
    }

    /**
     * Instantiates a new Base exception.
     *
     * @param code  the code
     * @param param the param
     * @param e     the e
     */
    public BaseException(String code, String[] param, Throwable e) {
        this(code, param, null, e);
    }

    /**
     * Instantiates a new Base exception.
     *
     * @param code    the code
     * @param errorId the error id
     * @param e       the e
     */
    public BaseException(String code, String errorId, Throwable e) {
        this(code, new String[] {}, errorId, e);

    }

    /**
     * Instantiates a new Base exception.
     *
     * @param code  the code
     * @param param the param
     */
    public BaseException(String code, String[] param) {
        this(code, param, "");
    }


    /**
     * Instantiates a new Base exception.
     *
     * @param code    the code
     * @param errorId the error id
     */
    public BaseException(String code, String errorId) {
        this(code, new String[] {}, errorId);
    }

    /**
     * Instantiates a new Base exception.
     *
     * @param code the code
     */
    public BaseException(String code) {
        this(code, new String[] {});
    }


    /**
     * Gets message id.
     *
     * @return the message id
     */
    public String getMessageId() {
        return messsageId;
    }

    /**
     * Gets field name.
     *
     * @return the field name
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Sets field name.
     *
     * @param errorId the error id
     */
    public void setFieldName(String errorId) {
        this.fieldName = errorId;
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
     * Sets message id.
     *
     * @param code the code
     */
    public void setMessageId(String code) {
        this.messsageId = code;
    }

    /**
     * Sets param.
     *
     * @param param the param
     */
    public void setParam(String[] param) {
        this.param = param;
    }

    /**
     * Is log output boolean.
     *
     * @return the boolean
     */
    public boolean isLogOutput() {
        return logOutput;
    }

    /**
     * Sets log output.
     *
     * @param logOutput the log output
     */
    public void setLogOutput(boolean logOutput) {
        this.logOutput = logOutput;
    }

    /**
     * Gets default message.
     *
     * @return the default message
     */
    public String getDefaultMessage() {
        return defaultMessage;
    }

    /**
     * Sets default message.
     *
     * @param defaultMessage the default message
     */
    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

}
