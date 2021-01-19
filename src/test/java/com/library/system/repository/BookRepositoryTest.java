package com.library.system.repository;

import com.library.system.bootstrap.LibraryManagementSystemApplication;
import com.library.system.domian.Author;
import com.library.system.domian.Book;
import com.library.system.exception.BookNotFoundException;
import com.library.system.exception.StudentNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    @Test
    @DisplayName("Should fetch all the book when asked from database")
    public void shouldFetchAllTheBook() throws BookNotFoundException {
        //Given data from db.changelog-04-t-book.yaml
        //When
        Optional<List<Book>> bookList = bookRepository.fetchAllBooks();
        //Then
        assertThat(bookList.get())
                .isNotNull()
                .extracting("bookName", "isbn", "quantity", "author")
                .contains(
                        tuple("Effective Java", "ISBN0001", 19, new Author(1L, "Joshua", "Bloch")),
                        tuple("Operating Systems", "ISBN0002", 25, new Author(2L, "Doug", "Lea")),
                        tuple("The Complete Reference", "ISBN0003", 50, new Author(3L, "Herbert", "Schildt")),
                        tuple("Thinking in Java", "ISBN0004", 21, new Author(4L, "Bruce", "Eckel")),
                        tuple("On Java 8", "ISBN0005", 50, new Author(4L, "Bruce", "Eckel"))
                );
    }

    @Test
    @DisplayName("Should fetch student details when asked by Id")
    public void shouldFetchStudentById() throws BookNotFoundException {
        //Given data from db.changelog-04-t-book.yaml
        Author author = Author.builder().Id(4L).firstName("Bruce").lastName("Eckel").build();
        Optional<Book> expectedBook = Optional.of(Book.builder().Id(4L).bookName("Thinking in Java").isbn("ISBN0004").quantity(21).author(author).build());
        //When
        Optional<Book> book = bookRepository.fetchBookById(4L);
        //Then
        assertThat(book)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expectedBook);
    }

    @Test
    @DisplayName("Should throw exception when there is no student with the given Id in database")
    public void shouldThrowExceptionWhenNoStudentsFoundWithTheGivenId() throws StudentNotFoundException {
        //Given data from db.changelog-04-t-book.yaml
        //When and Then
        assertThatThrownBy(() -> bookRepository.fetchBookById(100L)).isInstanceOf(BookNotFoundException.class)
                .hasMessageContaining("No Book registered with the given Id");
    }

    @Test
    @DisplayName("Should save book details into database")
    public void shouldSaveBookDetails() throws BookNotFoundException {
        //Given data from db.changelog-04-t-book.yaml
        Author author = Author.builder().Id(4L).firstName("Bruce").lastName("Eckel").build();
        Book saveBook = Book.builder().Id(6L).bookName("Thinking in C++").isbn("ISBN0006").quantity(26).author(author).build();
        //When
        Optional<Book> book = bookRepository.saveBook(saveBook);

        //Then
        assertThat(book).isNotNull().isNotEmpty().usingRecursiveComparison().isEqualTo(Optional.of(saveBook));
    }
}
