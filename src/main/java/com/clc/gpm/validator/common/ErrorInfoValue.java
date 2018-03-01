package com.clc.gpm.validator.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * The type Error info value.
 */
@Getter
@Setter
public class ErrorInfoValue implements Serializable {

    private static final long serialVersionUID = 5552835254119417468L;

    /**
     * item ID
     */
    private String errorId = null;

    /**
     * error message
     */
    private String errorMessage = null;

}
