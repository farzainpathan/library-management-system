package com.library.system.service;

import com.library.system.domian.Student;
import com.library.system.exception.StudentException;
import com.library.system.repository.StudentPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements RequestStudent{

    private final StudentPersistence studentPersistence;

    @Autowired
    public StudentService(StudentPersistence studentPersistence) {
        this.studentPersistence = studentPersistence;
    }

    @Override
    public List<Student> fetchAllStudents() throws StudentException {
        return studentPersistence.fetchAllStudents();
    }
}
