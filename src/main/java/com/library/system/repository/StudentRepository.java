package com.library.system.repository;

import com.library.system.Entity.StudentEntity;
import com.library.system.dao.StudentDao;
import com.library.system.domian.Student;
import com.library.system.exception.StudentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StudentRepository implements StudentPersistence {

    private final StudentDao studentDao;

    @Autowired
    public StudentRepository(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public Optional<Student> saveStudent(Student student) {
        StudentEntity student1 = studentDao.save(StudentEntity.createEntity(student));
        return Optional.of(StudentEntity.toModel(student1));
    }

    @Override
    public Optional<List<Student>> fetchAllStudents() throws StudentException {
        List<StudentEntity> studentList = studentDao.findAll();
        if (studentList.isEmpty())
            throw new StudentException("There are no registered students");
        else
            return Optional.of(StudentEntity.toModel(studentList));
    }

    @Override
    public Optional<Student> fetchStudentById(Long id) throws StudentException {
        Optional<StudentEntity> student = studentDao.findById(id);
        if (student.isPresent())
            return Optional.of(StudentEntity.toModel(student.get()));
        else
            throw new StudentException("There is no student registered with the given Id");
    }
}
