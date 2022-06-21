package com.library.system.service;

import com.library.system.domian.Book;
import com.library.system.exception.BookNotFoundException;

import java.util.List;
import java.util.Optional;

public interface RequestBook {

  Optional<List<Book>> fetchAllBooks() throws BookNotFoundException;

  Optional<Book> saveBook( Book book);

  Optional<Book> fetchBookById(Long id) throws BookNotFoundException;
}
