package com.library.system.controller;

import com.library.system.domian.Book;
import com.library.system.exception.BookException;
import com.library.system.service.RequestBook;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
@Api(value = "Book Endpoints")
public class BookController {

    private final RequestBook requestBook;

    @Autowired
    public BookController(RequestBook requestBook) {
        this.requestBook = requestBook;
    }

    @GetMapping("books")
    @ApiOperation(value = "This endpoint fetches all the books from database")
    public Optional<List<Book>> getAllBooks() throws BookException {
        return requestBook.fetchAllBooks();
    }


}
