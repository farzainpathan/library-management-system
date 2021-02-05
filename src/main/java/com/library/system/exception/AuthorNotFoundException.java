package com.library.system.exception;

import com.library.system.domian.Author;

public class AuthorNotFoundException extends Exception {

  public AuthorNotFoundException(Author author) {
    super("Invalid author Id | No author exist");
  }

  public AuthorNotFoundException(String msg) {
    super(msg);
  }
}
