package org.example.database_final.utils.exception;

/**
 * Custom exception class for handling scenarios where a user attempts an action without the necessary access permissions.
 * This class extends `RuntimeException`, making it suitable for runtime error handling in the application.
 */
public class DontHaveAccessException extends RuntimeException {

    /**
     * Constructor to create a new instance of `DontHaveAccessException`.
     * This exception is triggered when a user tries to perform an action they are not authorized to perform.
     */
    public DontHaveAccessException() {
        super("You cannot do that because you don't have access.");
    }
}
