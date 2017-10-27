package com.charles.admin.core;

public class InvalidArgumentException extends RuntimeException {
    public InvalidArgumentException() {
        super();
    }

    public InvalidArgumentException(final String s) {
        super(s);
    }

    public InvalidArgumentException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidArgumentException(final Throwable cause) {
        super(cause);
    }
}
