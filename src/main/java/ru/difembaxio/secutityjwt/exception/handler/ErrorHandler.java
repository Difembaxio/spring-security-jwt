package ru.difembaxio.secutityjwt.exception.handler;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.difembaxio.secutityjwt.exception.AccessDeniedException;
import ru.difembaxio.secutityjwt.exception.AuthenticationException;
import ru.difembaxio.secutityjwt.exception.EntityNotFoundException;
import ru.difembaxio.secutityjwt.exception.JwtTokenExpiredException;
import ru.difembaxio.secutityjwt.exception.JwtTokenInvalidException;
import ru.difembaxio.secutityjwt.exception.UserAlreadyExistsException;


@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({UserAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return ErrorMessage.builder()
            .error(HttpStatus.BAD_REQUEST.toString())
            .description(ex.getMessage())
            .build();
    }

    @ExceptionHandler({JwtTokenExpiredException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleUserJwtTokenExpiredException(JwtTokenExpiredException ex) {
        return ErrorMessage.builder()
            .error(HttpStatus.BAD_REQUEST.toString())
            .description(ex.getMessage())
            .build();
    }

    @ExceptionHandler({JwtTokenInvalidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleUserJwtTokenInvalidException(JwtTokenInvalidException ex) {
        return ErrorMessage.builder()
            .error(HttpStatus.BAD_REQUEST.toString())
            .description(ex.getMessage())
            .build();
    }

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleEntityNotFoundException(EntityNotFoundException ex) {
        return ErrorMessage.builder()
            .error(HttpStatus.BAD_REQUEST.toString())
            .description(ex.getMessage())
            .build();
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorMessage handleAuthenticationException(AuthenticationException ex) {
        return ErrorMessage.builder()
            .error(HttpStatus.UNAUTHORIZED.toString())
            .description(ex.getMessage())
            .build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage handleAccessDeniedException(AccessDeniedException ex) {
        return ErrorMessage.builder()
            .error(HttpStatus.FORBIDDEN.toString())
            .description(ex.getMessage())
            .build();
    }

}

