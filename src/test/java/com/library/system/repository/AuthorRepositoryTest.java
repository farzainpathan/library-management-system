package com.library.system.repository;

import com.library.system.bootstrap.LibraryManagementSystemApplication;
import com.library.system.domian.Author;
import com.library.system.exception.AuthorNotFoundException;
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
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void shouldStart() {
        assertThat(authorRepository).isNotNull();
    }

    @Test
    @DisplayName("Should fetch all the authors details when asked from database")
    public void shouldFetchAllTheAuthors() throws AuthorNotFoundException {
        //Given data from db.changelog-03-t-author.yaml
        //When
        Optional<List<Author>> authorList = authorRepository.fetchAllAuthor();
        //Then
        assertThat(authorList.get())
                .isNotNull()
                .hasSizeGreaterThanOrEqualTo(4)
                .extracting("Id", "firstName", "lastName")
                .contains(
                        tuple(1L, "Joshua", "Bloch"),
                        tuple(2L, "Doug", "Lea"),
                        tuple(3L, "Herbert", "Schildt"),
                        tuple(4L, "Bruce", "Eckel")
                );
    }

    @Test
    @DisplayName("Should get author details when asked given Id")
    public void shouldReturnAuthorDetailsById() throws AuthorNotFoundException {
        //Given data from db.changelog-03-t-author.yaml
        Author expectedAuthor = Author.builder().Id(3L).firstName("Herbert").lastName("Schildt").build();
        //When
        Author author = authorRepository.fetchAuthorById(3L);
        //Then
        assertThat(author)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expectedAuthor);
    }

    @Test
    @DisplayName("Should throw exception when invalid author id is given")
    public void shouldThrowExceptionWhenInvalidIdProvided() throws AuthorNotFoundException {
        //Given data from db.changelog-03-t-author.yaml
        //When and Then
        assertThatThrownBy(() -> authorRepository.fetchAuthorById(20L)).isInstanceOf(AuthorNotFoundException.class)
                .hasMessageContaining("No author found with the given Id");
    }

    @Test
    @DisplayName("Should save the department when asked")
    public void shouldSaveDepartment() throws AuthorNotFoundException {
        //Given data from db.changelog-03-t-author.yaml
        Author author = Author.builder().Id(5L).firstName("Richard").lastName("Warburton").build();
        //When
        Optional<Author> saveAuthor = authorRepository.saveAuthor(author);
        Optional<List<Author>> authorList = authorRepository.fetchAllAuthor();
        //Then
        assertThat(authorList.get())
                .isNotNull()
                .hasSizeGreaterThanOrEqualTo(5)
                .extracting("Id", "firstName", "lastName")
                .contains(
                        tuple(5L, "Richard", "Warburton")
                );
    }
}
