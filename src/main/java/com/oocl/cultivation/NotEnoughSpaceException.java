package com.oocl.cultivation;

public class NotEnoughSpaceException extends Exception {
    @Override
    public String getMessage() {
        return "Not Enough Space";
    }
}
