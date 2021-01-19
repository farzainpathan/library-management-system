package com.library.system.repository;

import com.library.system.entity.BookEntity;
import com.library.system.dao.BookDao;
import com.library.system.domian.Book;
import com.library.system.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Optional<List<Book>> fetchAllBooks() throws BookNotFoundException {
        List<BookEntity> bookList = bookDao.findAll();
        if (bookList.isEmpty())
            throw new BookNotFoundException("There are no registered books");
        else
            return Optional.of(BookEntity.toModel(bookList));
    }

    @Override
    public Optional<Book> fetchBookById(Long id) throws BookNotFoundException {
        Optional<BookEntity> book = bookDao.findById(id);
        if (book.isPresent())
            return Optional.of(BookEntity.toModel(book.get()));
        else
            throw new BookNotFoundException("No Book registered with the given Id");
    }
}
