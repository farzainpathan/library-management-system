package com.library.system.repository;

import com.library.system.domian.Author;
import com.library.system.domian.Department;
import com.library.system.exception.AuthorException;
import com.library.system.exception.DepartmentException;

import java.util.List;
import java.util.Optional;

public interface AuthorPersistence {

    Optional<Author> saveAuthor(Author author);

    Optional<List<Author>> fetchAllAuthor() throws AuthorException;

    Author fetchAuthorById(Long authorId) throws AuthorException;
}
