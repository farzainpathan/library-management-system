package com.library.system.repository;

import com.library.system.Entity.BookEntity;
import com.library.system.dao.BookDao;
import com.library.system.domian.Book;
import com.library.system.exception.BookException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookRRepository implements BookPersistence {

    private final BookDao bookDao;

    @Autowired
    public BookRRepository(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Optional<List<Book>> fetchAllBooks() throws BookException {
       throw new BookException("Yet to be implemented by Farzain!!!");
    }
}
