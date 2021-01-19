package com.library.system.repository;

import com.library.system.domian.Author;
import com.library.system.exception.AuthorNotFoundException;

import java.util.List;
import java.util.Optional;

public interface AuthorPersistence {

    Optional<Author> saveAuthor(Author author);

    Optional<List<Author>> fetchAllAuthor() throws AuthorNotFoundException;

    Author fetchAuthorById(Long authorId) throws AuthorNotFoundException;
}
