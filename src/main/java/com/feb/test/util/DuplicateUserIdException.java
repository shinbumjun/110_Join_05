package com.feb.test.util;

public class DuplicateUserIdException extends Exception {
    public DuplicateUserIdException() {
        super();
    }

    public DuplicateUserIdException(String message) {
        super(message);
    }

    public DuplicateUserIdException(String message, Throwable cause) {
        super(message, cause);
    }
}