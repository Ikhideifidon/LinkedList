package com.ikhideifidon;

public class EmptyArrayQueueException extends Exception {
    public EmptyArrayQueueException() { }
    public EmptyArrayQueueException(String message) {
        super(message);
    }
    public EmptyArrayQueueException(Throwable cause) {
        super(cause);
    }
    public EmptyArrayQueueException(String message, Throwable cause) {
        super(message, cause);
    }
    public EmptyArrayQueueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
