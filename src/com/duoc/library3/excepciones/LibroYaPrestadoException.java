
package com.duoc.library3.excepciones;

public class LibroYaPrestadoException extends Exception {

    public LibroYaPrestadoException() {
    }

    public LibroYaPrestadoException(String message) {
        super(message);
    }

    public LibroYaPrestadoException(String message, Throwable cause) {
        super(message, cause);
    }

    public LibroYaPrestadoException(Throwable cause) {
        super(cause);
    }

    public LibroYaPrestadoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
