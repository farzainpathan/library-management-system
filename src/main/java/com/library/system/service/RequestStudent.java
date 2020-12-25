package com.library.system.service;

import com.library.system.domian.Student;
import com.library.system.exception.StudentException;

import java.util.List;

public interface RequestStudent {

    public List<Student> fetchAllStudents() throws StudentException;
}
