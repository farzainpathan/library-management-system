package com.library.system.repository;

import com.library.system.domian.Book;
import com.library.system.exception.BookNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BookPersistence {

  Optional<Book> saveBook(Book book);

  Optional<List<Book>> fetchAllBooks() throws BookNotFoundException;

  Optional<Book> fetchBookById(Long id) throws BookNotFoundException;
}
