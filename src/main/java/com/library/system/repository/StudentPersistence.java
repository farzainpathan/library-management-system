package com.library.system.repository;

import com.library.system.domian.Student;
import com.library.system.exception.StudentException;

import java.util.List;
import java.util.Optional;

public interface StudentPersistence {

    Optional<Student> saveStudent(Student student);

    void deleteStudent(Long id);

    Optional<List<Student>> fetchAllStudents() throws StudentException;

    Optional<Student> fetchStudentById(Long id) throws StudentException;

    Optional<Student> fetchStudentByUsn(String usn) throws StudentException;
}
