package com.library.system.repository;

import com.library.system.bootstrap.LibraryManagementSystemApplication;
import com.library.system.domian.Student;
import com.library.system.exception.StudentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.groups.Tuple.tuple;

@SpringBootTest(classes = LibraryManagementSystemApplication.class)
public class studentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void shouldStart() {
        assertThat(studentRepository).isNotNull();
    }

    @Sql("/student.sql")
    @Test
    @DisplayName("Should fetch all students when asked from database")
    public void shouldFetchAllStudents() throws StudentException {
        //Given from student.sql
        //When
        Optional<List<Student>> studentList = studentRepository.fetchAllStudents();
        //Then
        assertThat(studentList.get())
                .isNotNull()
                .hasSize(12)
                .extracting("firstName", "lastName", "usn", "department")
                .contains(
                        tuple("Jonny", "Deep", "1PI12CS19", "CS"),
                        tuple("Christian", "Bale", "1PI12CS20", "IS")
                );
    }

    @Sql("/student.sql")
    @Test
    @DisplayName("Should fetch student when asked by Id from database")
    public void shouldFetchStudentByUsn() throws StudentException {
        //Given from student.sql
        Optional<Student> expectedStudent = Optional.of(Student.builder().Id(1L).firstName("Jonny").lastName("Deep").usn("1PI12CS19").department("CS").build());
        //When
        Optional<Student> student = studentRepository.fetchStudentById(1L);
        //Then
        assertThat(student)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expectedStudent);
    }

    @Sql("/student.sql")
    @Test
    @DisplayName("Should throw exception when there is no student with the given Id in database")
    public void shouldThrowExceptionWhenNoStudentsFoundWithTheGivenId() throws StudentException {
        //Given no data
        //When and Then
        assertThatThrownBy(() -> studentRepository.fetchStudentById(100L)).isInstanceOf(StudentException.class)
                .hasMessageContaining("There is no student registered with the given Id");
    }

    @Sql("/student.sql")
    @Test
    @DisplayName("Should save the student details when given in database")
    public void shouldSaveStudentDetails() throws StudentException {
        //Given
        //data from student.sql
        Student saveStudent = Student.builder().firstName("Farzain").lastName("Pathan").usn("1PI12CS36").department("MCA").build();
        //When
        Optional<Student> student = studentRepository.saveStudent(saveStudent);
        Optional<List<Student>> studentList = studentRepository.fetchAllStudents();
        //Then
        assertThat(studentList.get())
                .isNotEmpty()
                .isNotNull()
                .extracting("firstName", "lastName", "usn", "department")
                .contains(
                        tuple("Farzain", "Pathan", "1PI12CS36", "MCA")
                );
    }

    @Sql("/student.sql")
    @Test
    @DisplayName("Should fetch student details given USN when asked from database")
    public void shouldFetchStudentDetailsGivenUsn() throws StudentException {
        //Given data from student.sql
        Optional<Student> expectedStudent = Optional.of(Student.builder().Id(2L).firstName("Christian").lastName("Bale").usn("1PI12CS20").department("IS").build());
        //When
        Optional<Student> student = studentRepository.fetchStudentByUsn("1PI12CS20");
        //Then
        assertThat(student)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expectedStudent);
    }

    @Sql("/student.sql")
    @Test
    @DisplayName("Should throw exception when there is no student with the given USN in database")
    public void shouldThrowExceptionWhenNoStudentsFoundWithTheGivenUsn() throws StudentException {
        //Given data from student.sql
        //When and Then
        assertThatThrownBy(() -> studentRepository.fetchStudentByUsn("1PI12CS45")).isInstanceOf(StudentException.class)
                .hasMessageContaining("There is no student associated with the given USN");
    }

    @Sql("/student.sql")
    @Test
    @DisplayName("Should delete student when asked given Id from database")
    public void shouldDeleteStudentDetailsGivenId() throws StudentException {
        //Given data from student.sql
        //When
        Optional<List<Student>> beforeStudentList = studentRepository.fetchAllStudents();
        studentRepository.deleteStudent(2L);
        Optional<List<Student>> afterStudentList = studentRepository.fetchAllStudents();
        //Then
        assertThat(beforeStudentList.get()).hasSizeLessThanOrEqualTo(4);
        assertThat(afterStudentList.get()).hasSizeLessThanOrEqualTo(3);
    }
}
