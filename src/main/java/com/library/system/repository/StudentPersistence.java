package com.library.system.repository;

import com.library.system.domian.Student;
import com.library.system.exception.StudentException;

import java.util.List;
import java.util.Optional;

public interface StudentPersistence {

    public Optional<List<Student>> fetchAllStudents() throws StudentException;
}
