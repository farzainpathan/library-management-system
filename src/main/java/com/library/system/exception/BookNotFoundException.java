package com.library.system.exception;

import com.library.system.domian.Book;

public class BookNotFoundException extends Exception {

    public BookNotFoundException(Book book) {
        super("Invalid Id | No Book is associated with the given Id : " + book);
    }

    public BookNotFoundException(String message) {
        super(message);
    }
}
