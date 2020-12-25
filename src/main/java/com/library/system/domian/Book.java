package com.library.system.domian;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    private Long Id;

    private String bookName;

    private String authorName;

    private String isbn;

    private String quantity;
}
