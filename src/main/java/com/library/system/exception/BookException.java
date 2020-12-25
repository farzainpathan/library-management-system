package com.library.system.exception;

import com.library.system.domian.Book;

public class BookException extends Exception {

    public BookException(Book book) {
        super("Invalid Id | No Book is associated with the given Id : " + book);
    }

    public BookException(String message) {
        super(message);
    }
}
