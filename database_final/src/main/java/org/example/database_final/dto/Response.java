package org.example.database_final.dto;

public class Response {
    String message;
    Object data;

    public Response(String message) {
        this.message = message;
    }

    public Response(Object data) {
        this.data = data;
    }

    public Response(String message, Object data) {
        this.message = message;
        this.data = data;
    }

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
