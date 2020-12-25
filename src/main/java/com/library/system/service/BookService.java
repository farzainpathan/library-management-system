package com.library.system.service;

import com.library.system.domian.Book;
import com.library.system.exception.BookException;
import com.library.system.repository.BookPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements RequestBook {

    private final BookPersistence bookPersistence;

    @Autowired
    public BookService(BookPersistence bookPersistence) {
        this.bookPersistence = bookPersistence;
    }

    @Override
    public List<Book> fetchAllBooks() throws BookException {
        return bookPersistence.fetchAllBooks();
    }
}
