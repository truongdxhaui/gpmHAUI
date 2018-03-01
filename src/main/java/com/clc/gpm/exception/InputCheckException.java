package com.clc.gpm.exception;


/**
 * The type Input check exception.
 */
public class InputCheckException extends BaseException {

    private static final long serialVersionUID = 1920098082744310558L;

    /**
     * Instantiates a new Input check exception.
     *
     * @param e the e
     */
    public InputCheckException(Throwable e) {
        super(e);
    }

    /**
     * Instantiates a new Input check exception.
     *
     * @param code    the code
     * @param param   the param
     * @param errorId the error id
     * @param e       the e
     */
    public InputCheckException(String code, String[] param, String errorId,
                               Throwable e) {
        super(code, param, e);
    }

    /**
     * Instantiates a new Input check exception.
     *
     * @param code    the code
     * @param param   the param
     * @param errorId the error id
     */
    public InputCheckException(String code, String[] param, String errorId) {
        super(code, param, errorId);
    }

    /**
     * Instantiates a new Input check exception.
     *
     * @param code  the code
     * @param param the param
     * @param e     the e
     */
    public InputCheckException(String code, String[] param, Throwable e) {
        super(code, param, e);
    }

    /**
     * Instantiates a new Input check exception.
     *
     * @param code    the code
     * @param errorId the error id
     * @param e       the e
     */
    public InputCheckException(String code, String errorId, Throwable e) {
        super(code, errorId, e);
    }

    /**
     * Instantiates a new Input check exception.
     *
     * @param code  the code
     * @param param the param
     */
    public InputCheckException(String code, String[] param) {
        super(code, param);
    }

    /**
     * Instantiates a new Input check exception.
     *
     * @param code    the code
     * @param errorId the error id
     */
    public InputCheckException(String code, String errorId) {
        super(code, errorId);
    }

    /**
     * Instantiates a new Input check exception.
     *
     * @param code the code
     */
    public InputCheckException(String code) {
        super(code);
    }

}
