package com.library.system.controller;

import com.library.system.domian.Book;
import com.library.system.exception.BookNotFoundException;
import com.library.system.service.RequestBook;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @ApiOperation(value = "This endpoint fetches all the books from database")
  @GetMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Book>> getAllBooks() throws BookNotFoundException {

    return requestBook
        .fetchAllBooks()
        .map(books -> ResponseEntity.status(HttpStatus.OK).body(books))
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @ApiOperation(value = "This endpoint fetch book details by id")
  @GetMapping(value = "/book/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> fetchBookById(@PathVariable Long id) throws BookNotFoundException {

    Optional<Book> result = requestBook.fetchBookById(id);
    if (result.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body("Book with the id : " + id + " not found");
  }

  @ApiOperation(value = "This endpoint save book details to database")
  @PostMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Book> saveBook(@RequestBody Book book) {

    return requestBook
        .saveBook(book)
        .map(value -> ResponseEntity.status(HttpStatus.CREATED).body(value))
        .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
  }
}
