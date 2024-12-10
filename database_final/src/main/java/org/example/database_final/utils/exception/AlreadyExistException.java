package org.example.database_final.utils.exception;

/**
 * Custom exception class for handling situations where an object with a specific parameter already exists.
 * This class extends `RuntimeException`, allowing it to be thrown and caught in the application at runtime.
 * The exception message indicates the specific object and parameter that caused the conflict.
 */
public class AlreadyExistException extends RuntimeException {

    /**
     * Constructor to create a new instance of `AlreadyExistException`.
     *
     * @param object    The name of the object in which the conflict occurred (e.g., "Category").
     * @param parameter The parameter that already exists (e.g., "name").
     */
    public AlreadyExistException(String object, String parameter) {
        super(object + " with that " + parameter + " is already exist.");
    }
}
