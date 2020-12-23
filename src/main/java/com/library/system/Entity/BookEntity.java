package com.library.system.Entity;

import javax.persistence.*;

@Entity
@Table(name = "T_BOOK", catalog = "Library")
public class BookEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;
}
