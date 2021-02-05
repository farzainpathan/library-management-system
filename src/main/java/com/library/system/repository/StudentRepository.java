package com.library.system.repository;

import com.library.system.dao.StudentDao;
import com.library.system.domian.Student;
import com.library.system.entity.StudentEntity;
import com.library.system.exception.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
    StudentEntity savedStudent = studentDao.save(StudentEntity.createEntity(student));
    return Optional.of(StudentEntity.toModel(savedStudent));
  }

  @Override
  public Optional<List<Student>> fetchAllStudents() throws StudentNotFoundException {
    List<StudentEntity> studentList = studentDao.findAll();
    if (studentList.isEmpty())
      throw new StudentNotFoundException("There are no registered students");
    else return Optional.of(StudentEntity.toModel(studentList));
  }

  @Override
  public Optional<Student> fetchStudentById(Long id) throws StudentNotFoundException {
    Optional<StudentEntity> student = studentDao.findById(id);
    if (student.isPresent()) return Optional.of(StudentEntity.toModel(student.get()));
    else throw new StudentNotFoundException("There is no student registered with the given Id");
  }
}
