package com.library.system.repository;

import com.library.system.domian.Student;
import com.library.system.exception.StudentNotFoundException;

import java.util.List;
import java.util.Optional;

public interface StudentPersistence {

    Optional<Student> saveStudent(Student student);

    Optional<List<Student>> fetchAllStudents() throws StudentNotFoundException;

    Optional<Student> fetchStudentById(Long id) throws StudentNotFoundException;
}
