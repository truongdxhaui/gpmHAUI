package com.clc.gpm.exception;

/**
 * <p>ファイル名 : BaseException</p>
 * <p>説明 : BaseException</p>
 * @author bp.truong.pq
 * @since 2017/11/25
 */
public class BaseException extends Exception {

    private static final long serialVersionUID = -3130750938679514286L;

    /**
     * エラーコード
     */
    private String messsageId = null;
    /**
     * エラー項目property
     */
    private String fieldName = null;

    /**
     * defaultMessage
     */
    private String defaultMessage = null;
    /**
     * エラーコードに対応するメッセージ引数
     */
    private String[] param = null;

    /**
     * 更新ログに出力するエラーかどうか判定するフラグ
     */
    private boolean logOutput = false;

    /**
     * BaseException
     * @param e Throwable
     */
    public BaseException(Throwable e) {
        super(e);
    }

    
    
    /**
     * BaseException
     * @see jp.co.tap.BaseException.exception.CSSException .CSSException (String
     *      code, String[] param, Throwable e)
     * @param code String
     * @param param String[]
     * @param e Throwable
     * @param errorId String
     */
    public BaseException(String code, String[] param, String errorId,
            Throwable e) {
        super(e);
        this.messsageId = code;
        this.param = param;
        this.fieldName = errorId;
    }

    /**
     * BaseException
     * @see jp.co.tap.BaseException.exception.CSSException .CSSException (String
     *      code, String[] param, Throwable e)
     * @param code String
     * @param param String[]
     * @param errorId String
     */ 
    public BaseException(String code, String[] param, String errorId) {
        this.messsageId = code;
        this.param = param;
        this.fieldName = errorId;
    }

    /**
     * BaseException
     * @see jp.co.tap.BaseException.exception.CSSException .CSSException (String
     *      code, String[] param, Throwable e)
     * @param code String
     * @param param String[]
     * @param e Throwable
     */
    public BaseException(String code, String[] param, Throwable e) {
        this(code, param, null, e);
    }

    /**
     * BaseException
     * @see jp.co.tap.BaseException.exception.CSSException .CSSException (String
     *      code, String[] param, Throwable e)
     * @param code String
     * @param errorId String
     * @param e Throwable
     */
    public BaseException(String code, String errorId, Throwable e) {
        this(code, new String[] {}, errorId, e);

    }

    /**
     * init attribute code and param
     * 
     * @param code String
     * @param param String[]
     */
    public BaseException(String code, String[] param) {
        this(code, param, "");
    }

    /**
     * BaseException
     * @param code String
     * @param errorId String
     */
    public BaseException(String code, String errorId) {
        this(code, new String[] {}, errorId);
    }

    /**
     * BaseException
     * @param code String
     */
    public BaseException(String code) {
        this(code, new String[] {});
    }

    /**
     * get exception code
     * 
     * @return String
     */
    public String getMessageId() {
        return messsageId;
    }

    /**
     * get error id
     * 
     * @return String
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Set Error ID
     * 
     * @param errorId String
     */
    public void setFieldName(String errorId) {
        this.fieldName = errorId;
    }

    /**
     * get error parameter
     * 
     * @return String[]
     */
    public String[] getParam() {
        return param;
    }

    /**
     * set error code
     * 
     * @param code String
     */
    public void setMessageId(String code) {
        this.messsageId = code;
    }

    /**
     * set error parameter
     * 
     * @param param String[]
     */
    public void setParam(String[] param) {
        this.param = param;
    }

    /**
     * get value of attribute logOutput
     * 
     * @return boolean
     */
    public boolean isLogOutput() {
        return logOutput;
    }

    /**
     * set value of attribute logOutput
     * 
     * @param logOutput boolean
     */
    public void setLogOutput(boolean logOutput) {
        this.logOutput = logOutput;
    }

    /**
     * 
     * <p>説明 : getDefaultMessage</p> 
     * @author : [bp.thien.nv]
     * @since : [2017/12/26]
     * @return String
     */
    public String getDefaultMessage() {
        return defaultMessage;
    }

    /**
     * 
     * <p>説明 : setDefaultMessage</p> 
     * @author : [bp.thien.nv]
     * @since : [2017/12/26]
     * @param defaultMessage String
     */
    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

}
