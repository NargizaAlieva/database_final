package org.example.database_final.utils.exception;

/**
 * Custom exception class for handling scenarios where a specific object is not found in the application.
 * This class extends `RuntimeException`, making it suitable for runtime error handling in the application.
 */
public class ObjectNotFoundException extends RuntimeException {

    /**
     * Constructor to create a new instance of `ObjectNotFoundException`.
     * This exception is triggered when a requested object (e.g., a course, instructor, category) cannot be found in the system.
     *
     * @param objectName The name of the object that was not found.
     */
    public ObjectNotFoundException(String objectName) {
        super(objectName + " is not found");
    }
}
