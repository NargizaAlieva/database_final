package org.example.database_final.dto;

/**
 * A generic response class used for encapsulating messages and data to be sent back from the backend to the frontend.
 * This class provides a unified structure for API responses, allowing for consistent response handling across different endpoints.
 * It can hold a message indicating the outcome of an operation and an optional data object that contains additional information.
 */
public class Response {
    /**
     * A message indicating the outcome of an operation, such as "Operation successful" or "Error occurred".
     */
    private String message;

    /**
     *  An optional data object that contains additional information related to the response,
     *  such as specific data requested by the user or additional context.
     */
    private Object data;

    /**
     * Constructor to initialize the response with both a message and data.
     *
     * @param message The message to set.
     * @param data    The data object to set.
     */
    public Response(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    /**
     * Getters and setters
     */

    public String getMessage() {
        return message;
    }

    public Response setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Response setData(Object data) {
        this.data = data;
        return this;
    }
}
