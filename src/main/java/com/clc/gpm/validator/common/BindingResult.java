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

    protected List<Throwable> errors = new LinkedList<Throwable>();


    public boolean hasErrors() {
        return this.errors.size() > 0;
    }


    public void addError(Throwable error) {
        this.errors.add(error);
    }


    public void addAllErrors(BindingResult errors) {
        this.errors.addAll(errors.getAllErrors());
    }


    public List<Throwable> getAllErrors() {
        return Collections.unmodifiableList(this.errors);
    }

    public void clearErrors() {
        errors = new LinkedList<Throwable>();
    }

}
