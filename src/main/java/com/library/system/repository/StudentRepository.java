package com.library.system.repository;

import com.library.system.dao.StudentDao;
import com.library.system.domian.Book;
import com.library.system.domian.Student;
import com.library.system.exception.StudentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentRepository implements StudentPersistence {

    private final StudentDao studentDao;

    @Autowired
    public StudentRepository(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> fetchAllStudents() throws StudentException {
        throw new StudentException("Yet to be implemented by Farzain!!");
    }
}
