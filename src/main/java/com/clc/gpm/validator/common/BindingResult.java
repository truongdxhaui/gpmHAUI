package com.clc.gpm.validator.common;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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

    /**
     * Error list
     */
    protected List<Throwable> errors = new LinkedList<Throwable>();

    /**
     * <p>説明 :hasErrors</p> 
     * @author : truong.dx
     * @since : 2017/12/26
     * @return boolean
     */
    public boolean hasErrors() {
        return this.errors.size() > 0;
    }


    /**
     * <p>説明 : addError</p> 
     * @author : truong.dx
     * @since : 2017/12/26
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
     * Clear error list
     */
    public void clearErrors() {
        errors = new LinkedList<Throwable>();
    }

}
