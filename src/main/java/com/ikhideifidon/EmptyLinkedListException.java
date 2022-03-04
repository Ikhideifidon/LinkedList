package com.ikhideifidon;


public class EmptyLinkedListException extends RuntimeException {
    public EmptyLinkedListException(String message) {
        super(message);
    }
    public EmptyLinkedListException(Throwable cause) {
        super(cause);
    }
    public EmptyLinkedListException(String message, Throwable cause) {
        super(message, cause);
    }
    public EmptyLinkedListException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public EmptyLinkedListException() {

    }
}
