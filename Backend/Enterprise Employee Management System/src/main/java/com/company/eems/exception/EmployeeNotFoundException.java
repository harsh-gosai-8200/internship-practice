package com.company.eems.exception;

/**
 * Thrown when an employee is not found in the system.
 */
public class EmployeeNotFoundException extends RuntimeException {

    /**
     * Constructs EmployeeNotFoundException with message.
     *
     * @param message exception message
     */
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
