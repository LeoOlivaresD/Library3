
package com.duoc.library3.excepciones;

public class LibroNoEcontradoException extends Exception{

    public LibroNoEcontradoException() {
    }

    public LibroNoEcontradoException(String message) {
        super(message);
    }

    public LibroNoEcontradoException(String message, Throwable cause) {
        super(message, cause);
    }

    public LibroNoEcontradoException(Throwable cause) {
        super(cause);
    }

    public LibroNoEcontradoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
