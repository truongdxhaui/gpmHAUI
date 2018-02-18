package com.clc.gpm.exception;

/**
 * <p>ファイル名 : InputCheckException</p>
 * <p>説明 : InputCheckException</p>
 * @author bp.truong.pq
 * @since 2017/11/25
 */
public class InputCheckException extends BaseException {

    private static final long serialVersionUID = 1920098082744310558L;

    /**
     * InputCheckException
     * @see common.exception.BaseException (Throwable)
     * @param e Throwable
     */
    public InputCheckException(Throwable e) {
        super(e);
    }

    /**
     * InputCheckException
     * @param code String
     * @param param String[]
     * @param e String
     * @param errorId String
     */
    public InputCheckException(String code, String[] param, String errorId,
            Throwable e) {
        super(code, param, e);
    }

    /**
     * InputCheckException
     * @see exception.BaseException (String, String[], String)
     * @param code String
     * @param param String[]
     * @param errorId String
     */
    public InputCheckException(String code, String[] param, String errorId) {
        super(code, param, errorId);
    }

    /**
     * InputCheckException
     * @see exception.BaseException (String, String[], Throwable)
     * @param code String
     * @param param String[]
     * @param e Throwable
     */
    public InputCheckException(String code, String[] param, Throwable e) {
        super(code, param, e);
    }

    /**
     * InputCheckException
     * @see exception.BaseException (String, String, Throwable)
     * @param code String
     * @param errorId String
     * @param e Throwable
     */
    public InputCheckException(String code, String errorId, Throwable e) {
        super(code, errorId, e);
    }

    /**
     * InputCheckException
     * @see exception.BaseException (String, String[])
     * @param code String
     * @param param String[]
     */
    public InputCheckException(String code, String[] param) {
        super(code, param);
    }

    /**
     * InputCheckException
     * @see exception.BaseException (String, String)
     * @param code String
     * @param errorId String
     */
    public InputCheckException(String code, String errorId) {
        super(code, errorId);
    }

    /**
     * InputCheckException
     * @see exception.BaseException (String)
     * @param code String
     */
    public InputCheckException(String code) {
        super(code);
    }

}
