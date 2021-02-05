package com.library.system.repository;

import com.library.system.dao.AuthorDao;
import com.library.system.domian.Author;
import com.library.system.entity.AuthorEntity;
import com.library.system.exception.AuthorNotFoundException;
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
  public Optional<List<Author>> fetchAllAuthor() throws AuthorNotFoundException {
    List<AuthorEntity> authorList = authorDao.findAll();
    if (authorList.isEmpty())
      throw new AuthorNotFoundException("No Author's details are present in database");
    else return Optional.of(AuthorEntity.toModel(authorList));
  }

  @Override
  public Author fetchAuthorById(Long authorId) throws AuthorNotFoundException {
    Optional<AuthorEntity> author = authorDao.findById(authorId);
    if (author.isPresent()) return AuthorEntity.toModel(author.get());
    else throw new AuthorNotFoundException("No author found with the given Id");
  }
}
