package com.azubike.ellipsis.spring_cache.errors;

public class BookNotFoundException extends RuntimeException {

  public BookNotFoundException() {}

  public BookNotFoundException(final String message) {
    super(message);
  }
}
