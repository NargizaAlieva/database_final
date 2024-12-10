package org.example.database_final.utils.exception;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(String objectName) {
        super(objectName + " is not found");
    }
}
