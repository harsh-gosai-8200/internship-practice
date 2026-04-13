package com.company.eems.exception;

/**
 * Thrown when invalid employee data is provided.
 */
public class InvalidEmployeeDataException extends RuntimeException {

    /**
     * Constructs InvalidEmployeeDataException with message.
     *
     * @param message exception message
     */
    public InvalidEmployeeDataException(String message) {
        super(message);
    }
}
