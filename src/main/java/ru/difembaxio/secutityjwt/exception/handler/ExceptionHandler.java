package ru.difembaxio.secutityjwt.exception.handler;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.difembaxio.secutityjwt.exception.AccessDeniedException;
import ru.difembaxio.secutityjwt.exception.AuthenticationException;
import ru.difembaxio.secutityjwt.exception.EntityNotFoundException;
import ru.difembaxio.secutityjwt.exception.JwtTokenExpiredException;
import ru.difembaxio.secutityjwt.exception.JwtTokenInvalidException;
import ru.difembaxio.secutityjwt.exception.UserAlreadyExistsException;


@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({UserAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMessage handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return ExceptionMessage.builder()
            .error(HttpStatus.BAD_REQUEST.toString())
            .description(ex.getMessage())
            .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({JwtTokenExpiredException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMessage handleUserJwtTokenExpiredException(JwtTokenExpiredException ex) {
        return ExceptionMessage.builder()
            .error(HttpStatus.BAD_REQUEST.toString())
            .description(ex.getMessage())
            .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({JwtTokenInvalidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMessage handleUserJwtTokenInvalidException(JwtTokenInvalidException ex) {
        return ExceptionMessage.builder()
            .error(HttpStatus.BAD_REQUEST.toString())
            .description(ex.getMessage())
            .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMessage handleEntityNotFoundException(EntityNotFoundException ex) {
        return ExceptionMessage.builder()
            .error(HttpStatus.BAD_REQUEST.toString())
            .description(ex.getMessage())
            .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionMessage handleAuthenticationException(AuthenticationException ex) {
        return ExceptionMessage.builder()
            .error(HttpStatus.UNAUTHORIZED.toString())
            .description(ex.getMessage())
            .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionMessage handleAccessDeniedException(AccessDeniedException ex) {
        return ExceptionMessage.builder()
            .error(HttpStatus.FORBIDDEN.toString())
            .description(ex.getMessage())
            .build();
    }

}

