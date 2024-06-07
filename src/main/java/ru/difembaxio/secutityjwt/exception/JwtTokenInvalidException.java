package ru.difembaxio.secutityjwt.exception;

public class JwtTokenInvalidException extends RuntimeException {

    public JwtTokenInvalidException(String message) {
        super(message);
    }
}
