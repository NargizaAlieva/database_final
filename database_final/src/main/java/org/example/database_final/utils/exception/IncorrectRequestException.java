package org.example.database_final.utils.exception;

/**
 * Custom exception class for handling scenarios where an incoming request is incorrect or missing required parameters.
 * This class extends `RuntimeException`, making it suitable for runtime error handling in the application.
 */
public class IncorrectRequestException extends RuntimeException {

    /**
     * Constructor to create a new instance of `IncorrectRequestException`.
     * This exception is triggered when a user provides an invalid or incomplete request, such as missing necessary parameters.
     *
     * @param parameter The name of the missing parameter in the request.
     */
    public IncorrectRequestException(String parameter) {
        super("You need to provide " + parameter);
    }
}
