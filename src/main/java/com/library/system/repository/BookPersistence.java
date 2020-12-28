package com.library.system.repository;

import com.library.system.domian.Book;
import com.library.system.domian.Student;
import com.library.system.exception.BookException;

import java.util.List;
import java.util.Optional;

public interface BookPersistence {

    Optional<Book> saveBook(Book book);

    void deleteBookById(Long id);

    Optional<List<Book>> fetchAllBooks() throws BookException;

    Optional<Book> fetchBookById(Long id) throws BookException;

    Optional<Book> fetchBookByIsbn(String isbn) throws BookException;
}
