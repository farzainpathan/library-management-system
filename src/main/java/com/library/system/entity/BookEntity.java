package com.library.system.entity;

import com.library.system.domian.Author;
import com.library.system.domian.Book;
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
@Table(name = "T_BOOK")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long Id;

    @Column(name = "BOOK_NAME")
    private String bookName;

    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "QUANTITY")
    private int quantity;

    @ManyToOne(targetEntity = AuthorEntity.class)
    @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID")
    private AuthorEntity authorEntity;

    public static List<Book> toModel(List<BookEntity> bookEntityList) {
        return bookEntityList.stream().map(BookEntity::toModel).collect(Collectors.toList());
    }

    public static Book toModel(BookEntity bookEntity) {
        return Book.builder()
                .Id(bookEntity.getId())
                .bookName(bookEntity.getBookName())
                .isbn(bookEntity.getIsbn())
                .quantity(bookEntity.getQuantity())
                .author(BookEntity.toModel(bookEntity.getAuthorEntity()))
                .build();
    }

    public static Author toModel(AuthorEntity authorEntity) {
        return Author.builder()
                .Id(authorEntity.getId())
                .firstName(authorEntity.getFirstName())
                .lastName(authorEntity.getLastName())
                .build();
    }
    public static BookEntity createEntity(Book book) {
        return BookEntity.builder()
                .bookName(book.getBookName())
                .isbn(book.getIsbn())
                .quantity(book.getQuantity())
                .authorEntity(BookEntity.createEntity(book.getAuthor()))
                .build();
    }

    private static AuthorEntity createEntity(Author author) {
        return AuthorEntity.builder()
                .Id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .build();
    }
}
