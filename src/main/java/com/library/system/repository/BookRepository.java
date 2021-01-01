package com.library.system.repository;

import com.library.system.Entity.BookEntity;
import com.library.system.Entity.StudentEntity;
import com.library.system.dao.BookDao;
import com.library.system.domian.Book;
import com.library.system.domian.Student;
import com.library.system.exception.BookException;
import com.library.system.exception.StudentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookRepository implements BookPersistence {

    private final BookDao bookDao;

    @Autowired
    public BookRepository(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Optional<Book> saveBook(Book book) {
        BookEntity bookEntity = bookDao.save(BookEntity.createEntity(book));
        return Optional.of(BookEntity.toModel(bookEntity));
    }

    @Override
    public void deleteBookById(Long id) {
        bookDao.deleteById(id);
    }

    @Override
    public Optional<List<Book>> fetchAllBooks() throws BookException {
        List<BookEntity> bookList = bookDao.findAll();
        if (bookList.isEmpty())
            throw new BookException("There are no registered books");
        else
            return Optional.of(BookEntity.toModel(bookList));
    }

    @Override
    public Optional<Book> fetchBookById(Long id) throws BookException {
        Optional<BookEntity> book = bookDao.findById(id);
        if (book.isPresent())
            return Optional.of(BookEntity.toModel(book.get()));
        else
            throw new BookException("No Book registered with the given Id");
    }

    @Override
    public Optional<Book> fetchBookByIsbn(String isbn) throws BookException {
        Example<BookEntity> bookIsbn = Example.of(BookEntity.builder().isbn(isbn).build());
        Optional<BookEntity> book = bookDao.findOne(bookIsbn);
        if (book.isPresent())
            return Optional.of(BookEntity.toModel(book.get()));
        else
            throw new BookException("There is no books associated with the given ISBN");
    }
}
