package com.library.system.repository;

import com.library.system.domian.Book;
import com.library.system.exception.BookException;

import java.util.List;

public interface BookPersistence {

    public List<Book> fetchAllBooks() throws BookException;
}
