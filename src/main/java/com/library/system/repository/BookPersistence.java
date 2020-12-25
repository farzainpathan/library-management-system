package com.library.system.repository;

import com.library.system.domian.Book;
import com.library.system.exception.BookException;

import java.util.List;
import java.util.Optional;

public interface BookPersistence {

    public Optional<List<Book>> fetchAllBooks() throws BookException;
}
