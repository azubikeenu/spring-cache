package com.azubike.ellipsis.spring_cache.web.controllers.aop;

import com.azubike.ellipsis.spring_cache.errors.BookNotFoundException;
import com.azubike.ellipsis.spring_cache.errors.response.ErrorMessage;
import java.util.Date;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlers {

  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<Object> handleException(Exception ex, WebRequest req) {
    String path = ((ServletWebRequest) req).getRequest().getRequestURI();

    ErrorMessage errorMessage =
        new ErrorMessage(
            new Date(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), path);
    return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(BookNotFoundException.class)
  public ResponseEntity<Object> validationErrorHandler(BookNotFoundException ex, WebRequest req) {
    String path = ((ServletWebRequest) req).getRequest().getRequestURI();
    ErrorMessage errorMessage =
        new ErrorMessage(new Date(), ex.getMessage(), HttpStatus.NOT_FOUND.value(), path);
    return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }
}
