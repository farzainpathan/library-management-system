package com.library.system.repository;

import com.library.system.bootstrap.LibraryManagementSystemApplication;
import com.library.system.domian.Book;
import com.library.system.domian.Student;
import com.library.system.exception.BookException;
import com.library.system.exception.StudentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.groups.Tuple.tuple;

@SpringBootTest(classes = LibraryManagementSystemApplication.class)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void shouldStart() {
        assertThat(bookRepository).isNotNull();
    }

    @Sql("/book.sql")
    @Test
    @DisplayName("Should fetch all the book when asked from database")
    public void shouldFetchAllTheBook() throws BookException {
        //Given from student.sql
        //When
        Optional<List<Book>> bookList = bookRepository.fetchAllBooks();
        //Then
        assertThat(bookList.get())
                .isNotNull()
                .isNotNull()
                .extracting("bookName", "authorName", "isbn", "quantity")
                .contains(
                        tuple("Clean Code", "Robert C. Martin", "PSI156GH", 6),
                        tuple("Clean Architecture", "Robert C. Martin", "TUS456KL", 16)
                );
    }

    @Sql("/book.sql")
    @Test
    @DisplayName("Should fetch book when asked by Id from database")
    public void shouldFetchBookById() throws BookException {
        //Given from student.sql
        Optional<Book> expectedBook = Optional.of(Book.builder().Id(1L).bookName("Clean Code").authorName("Robert C. Martin").isbn("PSI156GH").quantity(6).build());
        //When
        Optional<Book> book = bookRepository.fetchBookById(1L);
        //Then
        assertThat(book)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expectedBook);
    }

    @Sql("/book.sql")
    @Test
    @DisplayName("Should throw exception when asked by Id not valid")
    public void shouldThrowExceptionWhenInvalidId() throws BookException {
        //Given data from student.sql
        //When and Then
        assertThatThrownBy(() -> bookRepository.fetchBookById(10L)).isInstanceOf(BookException.class)
                .hasMessageContaining("No Book registered with the given Id");
    }

    @Sql("/book.sql")
    @Test
    @DisplayName("Should save book details into database")
    public void shouldSaveBookDetails() throws BookException {
        //Given data from student.sql
        Book saveBook = Book.builder().bookName("Code").authorName("Farzain").isbn("PSI1919").quantity(6).build();
        //When
        Optional<Book> book = bookRepository.saveBook(saveBook);

        Optional<List<Book>> bookList = bookRepository.fetchAllBooks();
        //Then
        assertThat(bookList.get())
                .isNotEmpty()
                .isNotNull()
                .extracting("bookName", "authorName", "isbn", "quantity")
                .contains(
                        tuple("Code", "Farzain", "PSI1919", 6)
                );
    }

    @Sql("/book.sql")
    @Test
    @DisplayName("Should delete book when asked given Id from database")
    public void shouldDeleteBookDetailsGivenId() throws BookException {
        //Given data from student.sql
        //When
        Optional<List<Book>> beforeBookList = bookRepository.fetchAllBooks();
        bookRepository.deleteBookById(2L);
        Optional<List<Book>> afterBookList = bookRepository.fetchAllBooks();
        //Then
        assertThat(beforeBookList.get()).hasSizeLessThanOrEqualTo(11);
        assertThat(afterBookList.get()).hasSizeLessThanOrEqualTo(10);
    }

    @Sql("/book.sql")
    @Test
    @DisplayName("Should throw exception when there is no book with the given isbn in database")
    public void shouldThrowExceptionWhenNoStudentsFoundWithTheGivenUsn() throws BookException {
        //Given data from student.sql
        //When and Then
        assertThatThrownBy(() -> bookRepository.fetchBookByIsbn("PIKU8989")).isInstanceOf(BookException.class)
                .hasMessageContaining("There is no books associated with the given ISBN");
    }
}
