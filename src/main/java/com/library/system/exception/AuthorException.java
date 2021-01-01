package com.library.system.exception;

import com.library.system.domian.Author;

public class AuthorException extends Exception {

    public AuthorException(Author author) {
        super("Invalid author Id | No author exist");
    }

    public AuthorException(String msg) {
        super(msg);
    }
}
