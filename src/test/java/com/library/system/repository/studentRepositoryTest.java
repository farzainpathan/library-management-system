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

    @Test
    @DisplayName("Should fetch all students when asked from database")
    public void shouldFetchAllStudents() throws StudentException {
        //Given data from db.changelog-02-t-student.yaml
        //When
        Optional<List<Student>> studentList = studentRepository.fetchAllStudents();
        //Then
        assertThat(studentList.get())
                .isNotNull()
                .hasSize(7)
                .extracting("firstName", "lastName", "usn", "departmentId")
                .contains(
                        tuple("Farzain", "Pathan", "1PI12CS09", 1L),
                        tuple("Umesh", "Yadav", "1PI12IS49", 2L),
                        tuple("Gautam", "Pai", "1PI12MCA43", 3L),
                        tuple("Ayush", "Agrawal", "1PI12CS20", 1L),
                        tuple("Sarath", "PM", "1PI12IS38", 2L),
                        tuple("Anil", "Thapilaya", "1PI12MCA13", 3L),
                        tuple("Arjun", "Sharma", "1PI12CS19", 1L)
                );
    }

    @Test
    @DisplayName("Should fetch student details when asked by Id")
    public void shouldFetchStudentByUsn() throws StudentException {
        //Given data from db.changelog-02-t-student.yaml
        Optional<Student> expectedStudent = Optional.of(Student.builder().Id(6L).firstName("Anil").lastName("Thapilaya").usn("1PI12MCA13").departmentId(3L).build());
        //When
        Optional<Student> student = studentRepository.fetchStudentById(6L);
        //Then
        assertThat(student)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expectedStudent);
    }

    @Test
    @DisplayName("Should throw exception when there is no student with the given Id in database")
    public void shouldThrowExceptionWhenNoStudentsFoundWithTheGivenId() throws StudentException {
        //Given data from db.changelog-02-t-student.yaml
        //When and Then
        assertThatThrownBy(() -> studentRepository.fetchStudentById(100L)).isInstanceOf(StudentException.class)
                .hasMessageContaining("There is no student registered with the given Id");
    }
    /*

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

    */
}
