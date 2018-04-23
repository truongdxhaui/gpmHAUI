package com.clc.gpm.validator.common;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * The type Binding result.
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
     * Has errors boolean.
     *
     * @return the boolean
     */
    public boolean hasErrors() {
        return this.errors.size() > 0;
    }


    /**
     * Add error.
     *
     * @param error the error
     */
    public void addError(Throwable error) {
        this.errors.add(error);
    }


    /**
     * Add all errors.
     *
     * @param errors the errors
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
