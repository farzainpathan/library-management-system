package com.library.system.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name = "T_STUDENT", catalog = "Library")
public class StudentEntity {

    @javax.persistence.Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;
}
