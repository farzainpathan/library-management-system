package com.library.system.service;

import com.library.system.domian.Book;
import com.library.system.exception.BookException;

import java.util.List;

public interface RequestBook {

    public List<Book> fetchAllBooks() throws BookException;
}
