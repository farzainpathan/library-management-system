package com.library.system.service;

import com.library.system.domian.Book;
import com.library.system.exception.BookNotFoundException;
import com.library.system.repository.BookPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements RequestBook {

  private final BookPersistence bookPersistence;

  @Autowired
  public BookService(BookPersistence bookPersistence) {
    this.bookPersistence = bookPersistence;
  }

  @Override
  public Optional<List<Book>> fetchAllBooks() throws BookNotFoundException {
    return bookPersistence.fetchAllBooks();
  }

  @Override
  public Optional<Book> saveBook(Book book) {
    return bookPersistence.saveBook(book);
  }

  @Override
  public Optional<Book> fetchBookById(Long id) throws BookNotFoundException {
    return bookPersistence.fetchBookById(id);
  }
}
