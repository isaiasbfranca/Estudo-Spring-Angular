package br.com.newgaragem.core.exception;


public class CustomNotFoundException extends RuntimeException {

    public CustomNotFoundException(String msg) {
        super(msg);
    }
}
