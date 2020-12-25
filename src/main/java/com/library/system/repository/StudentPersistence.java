package com.library.system.repository;

import com.library.system.domian.Student;
import com.library.system.exception.StudentException;

import java.util.List;

public interface StudentPersistence {

    public List<Student> fetchAllStudents() throws StudentException;
}
