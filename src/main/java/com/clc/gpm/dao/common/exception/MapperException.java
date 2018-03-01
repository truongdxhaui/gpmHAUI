package com.clc.gpm.dao.common.exception;


/**
 * The type Mapper exception.
 */
public class MapperException extends RuntimeException {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * MapperException
     */
    public MapperException() {
        super();
    }

    /**
     * MapperException
     *
     * @param message String
     */
    public MapperException(String message) {
        super(message);
    }

    /**
     * MapperException
     *
     * @param message String
     * @param cause   Throwable
     */
    public MapperException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * MapperException
     *
     * @param cause Throwable
     */
    public MapperException(Throwable cause) {
        super(cause);
    }

}
