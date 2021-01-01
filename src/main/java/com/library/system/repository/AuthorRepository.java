package com.library.system.repository;

import com.library.system.Entity.AuthorEntity;
import com.library.system.dao.AuthorDao;
import com.library.system.domian.Author;
import com.library.system.exception.AuthorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AuthorRepository implements AuthorPersistence {

    private final AuthorDao authorDao;

    @Autowired
    public AuthorRepository(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public Optional<Author> saveAuthor(Author author) {
        AuthorEntity authorEntity = authorDao.save(AuthorEntity.createEntity(author));
        return Optional.of(AuthorEntity.toModel(authorEntity));
    }

    @Override
    public Optional<List<Author>> fetchAllAuthor() throws AuthorException {
        List<AuthorEntity> authorList = authorDao.findAll();
        if (authorList.isEmpty())
            throw new AuthorException("No Author's details are present in database");
        else
            return Optional.of(AuthorEntity.toModel(authorList));
    }

    @Override
    public Author fetchAuthorById(Long authorId) throws AuthorException {
        Optional<AuthorEntity> author = authorDao.findById(authorId);
        if (author.isPresent())
            return AuthorEntity.toModel(author.get());
        else
            throw new AuthorException("No author found with the given Id");
    }
}