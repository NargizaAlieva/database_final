package org.example.database_final.utils.exception;

public class AlreadyExistException extends RuntimeException{
    public AlreadyExistException(String object, String parameter) {
        super(object + " with that " + parameter + " is already exist.");
    }
}
