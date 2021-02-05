package com.library.system.repository;

import com.library.system.bootstrap.LibraryManagementSystemApplication;
import com.library.system.domian.Department;
import com.library.system.domian.Student;
import com.library.system.exception.StudentNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.groups.Tuple.tuple;

@SpringBootTest(classes = LibraryManagementSystemApplication.class)
public class studentRepositoryTest {

  @Autowired private StudentRepository studentRepository;

  @Test
  public void shouldStart() {
    assertThat(studentRepository).isNotNull();
  }

  @Test
  @DisplayName("Should fetch all students when asked from database")
  public void shouldFetchAllStudents() throws StudentNotFoundException {
    // Given data from db.changelog-02-t-student.yaml
    // When
    Optional<List<Student>> studentList = studentRepository.fetchAllStudents();
    // Then
    assertThat(studentList.get())
        .isNotNull()
        .hasSizeGreaterThanOrEqualTo(7)
        .extracting("firstName", "lastName", "usn", "department")
        .contains(
            tuple("Farzain", "Pathan", "1PI12CS09", new Department(1L, "CS", "Paul Williams")),
            tuple("Umesh", "Yadav", "1PI12IS49", new Department(2L, "IS", "Sandeep Shinde")),
            tuple("Gautam", "Pai", "1PI12MCA43", new Department(3L, "MCA", "Don Eluvathingal")),
            tuple("Ayush", "Agrawal", "1PI12CS20", new Department(1L, "CS", "Paul Williams")),
            tuple("Sarath", "PM", "1PI12IS38", new Department(2L, "IS", "Sandeep Shinde")),
            tuple("Anil", "Thapilaya", "1PI12MCA13", new Department(3L, "MCA", "Don Eluvathingal")),
            tuple("Arjun", "Sharma", "1PI12CS19", new Department(1L, "CS", "Paul Williams")));
  }

  @Test
  @DisplayName("Should fetch student details when asked by Id")
  public void shouldFetchStudentById() throws StudentNotFoundException {
    // Given data from db.changelog-02-t-student.yaml
    Department department =
        Department.builder().Id(3L).departmentName("MCA").hodName("Don Eluvathingal").build();
    Optional<Student> expectedStudent =
        Optional.of(
            Student.builder()
                .Id(6L)
                .firstName("Anil")
                .lastName("Thapilaya")
                .usn("1PI12MCA13")
                .department(department)
                .build());
    // When
    Optional<Student> student = studentRepository.fetchStudentById(6L);
    // Then
    assertThat(student).isNotNull().usingRecursiveComparison().isEqualTo(expectedStudent);
  }

  @Test
  @DisplayName("Should throw exception when there is no student with the given Id in database")
  public void shouldThrowExceptionWhenNoStudentsFoundWithTheGivenId()
      throws StudentNotFoundException {
    // Given data from db.changelog-02-t-student.yaml
    // When and Then
    assertThatThrownBy(() -> studentRepository.fetchStudentById(100L))
        .isInstanceOf(StudentNotFoundException.class)
        .hasMessageContaining("There is no student registered with the given Id");
  }

  @Test
  @DisplayName("Should save the student details when given into database")
  public void shouldSaveStudentDetails() throws StudentNotFoundException {
    // Given data from db.changelog-02-t-student.yaml
    Department department =
        Department.builder().Id(1L).departmentName("CS").hodName("Paul Williams").build();
    Student saveStudent =
        Student.builder()
            .firstName("Umar")
            .lastName("Pathan")
            .usn("1PI12CS33")
            .department(department)
            .build();
    // When
    Optional<Student> student = studentRepository.saveStudent(saveStudent);
    Optional<List<Student>> studentList = studentRepository.fetchAllStudents();
    // Then
    assertThat(studentList.get())
        .isNotEmpty()
        .isNotNull()
        .hasSizeGreaterThanOrEqualTo(8)
        .extracting("firstName", "lastName", "usn", "department")
        .contains(tuple("Umar", "Pathan", "1PI12CS33", new Department(1L, "CS", "Paul Williams")));
  }
}
