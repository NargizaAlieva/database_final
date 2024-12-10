package org.example.database_final.utils.exception;

public class WasDeletedException extends RuntimeException{
    public WasDeletedException(String objectName) {
        super("This " + objectName + " was deleted");
    }

}
