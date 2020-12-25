package com.library.system.Entity;

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
@Table(name = "T_BOOK", catalog = "Library")
public class BookEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID")
    private Long Id;

    @Column(name = "BOOK_NAME")
    private String bookName;

    @Column(name = "AUTHOR_NAME")
    private String authorName;

    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "QUANTITY")
    private String quantity;

    public static List<Book> toModel(List<BookEntity> bookEntityList) {
        return bookEntityList.stream().map(BookEntity::toModel).collect(Collectors.toList());
    }

    public static Book toModel(BookEntity bookEntity) {
        return Book.builder()
                .bookName(bookEntity.getBookName())
                .authorName(bookEntity.getAuthorName())
                .isbn(bookEntity.getIsbn())
                .quantity(bookEntity.getQuantity())
                .build();
    }
}
