package com.library.system.entity;

import com.library.system.domian.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "T_AUTHOR")
public class AuthorEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long Id;

  @Column(name = "FIRST_NAME")
  private String firstName;

  @Column(name = "LAST_NAME")
  private String lastName;

  public static List<Author> toModel(List<AuthorEntity> authorList) {
    return authorList.stream().map(AuthorEntity::toModel).collect(Collectors.toList());
  }

  public static Author toModel(AuthorEntity authorEntity) {
    return Author.builder()
        .Id(authorEntity.getId())
        .firstName(authorEntity.getFirstName())
        .lastName(authorEntity.getLastName())
        .build();
  }

  public static AuthorEntity createEntity(Author author) {
    return AuthorEntity.builder()
        .Id(author.getId())
        .firstName(author.getFirstName())
        .lastName(author.getLastName())
        .build();
  }
}
