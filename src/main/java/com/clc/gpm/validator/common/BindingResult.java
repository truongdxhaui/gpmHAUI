package com.tau.validator.common;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tau.utils.MessageUtil;

/**
 * <p>ファイル名 : BindingResult</p>
 * <p>説明 : ErrorResult class contain info error</p>
 * @author bp.truong.pq
 * @since 2017/11/25
 */
@Component
// @Scope("request")
public class BindingResult implements Serializable {

    private static final long serialVersionUID = 5093266952737916926L;

    /** MessageUtilのインスタンス */
    @Autowired
    protected MessageUtil msgUtil;

    /**
     * Error list
     */
    protected List<Throwable> errors = new LinkedList<Throwable>();

    /**
     * <p>説明 :hasErrors</p> 
     * @author : [dts.bp34]
     * @since : [2017/12/26]
     * @return boolean
     */
    public boolean hasErrors() {
        return this.errors.size() > 0;
    }


    /**
     * <p>説明 : addError</p> 
     * @author : [dts.bp34]
     * @since : [2017/12/26]
     * @param error 
     */
    public void addError(Throwable error) {
        this.errors.add(error);
    }

    /**
     * Add all errors from the given {@code ErrorResult} instance to this
     * {@code ErrorResult} instance.
     * 
     * @param errors
     *            the {@code Errors} instance to merge in
     */
    public void addAllErrors(BindingResult errors) {
        this.errors.addAll(errors.getAllErrors());
    }

    /**
     * Get all errors.
     * 
     * @return List of {@link Throwable} instances
     */
    public List<Throwable> getAllErrors() {
        return Collections.unmodifiableList(this.errors);
    }

    /**
     * <p>説明 : getMessages</p> 
     * @author : [dts.bp34]
     * @since : [2017/12/26]
     * @return List<ErrorInfoValue>
     */
    public List<ErrorInfoValue> getMessages() {
        return msgUtil.getMessageList(this);
    }

    /**
     * Clear error list
     */
    public void clearErrors() {
        errors = new LinkedList<Throwable>();
    }

}
