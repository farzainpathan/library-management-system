package com.library.system.repository;

import com.library.system.bootstrap.LibraryManagementSystemApplication;
import com.library.system.domian.Department;
import com.library.system.exception.BookException;
import com.library.system.exception.DepartmentException;
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
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void shouldStart() {
        assertThat(departmentRepository).isNotNull();
    }

    @Test
    @DisplayName("Should fetch all the book when asked from database")
    public void shouldFetchAllTheBook() throws DepartmentException {
        //Given data from db.changelog-01-t-department.yaml
        //When
        Optional<List<Department>> departmentList = departmentRepository.fetchAllCourses();
        //Then
        assertThat(departmentList.get())
                .isNotNull()
                .hasSizeGreaterThan(3)
                .extracting("departmentName", "hodName")
                .contains(
                        tuple("CS", "Paul Williams"),
                        tuple("IS", "Sandeep Shinde"),
                        tuple("MCA", "Don Eluvathingal")
                );
    }

    @Test
    @DisplayName("Should save the department when asked")
    public void shouldSaveDepartment() throws DepartmentException {
        //Given
        Department department = Department.builder().departmentName("MBA").hodName("Narayana Swamy").build();
        //When
        Optional<Department> department1 = departmentRepository.saveDepartment(department);
        Optional<List<Department>> departmentList = departmentRepository.fetchAllCourses();
        //Then
        assertThat(departmentList.get())
                .isNotNull()
                .hasSize(4)
                .extracting("departmentName", "hodName")
                .contains(
                        tuple("MBA", "Narayana Swamy")
                );

    }

    @Test
    @DisplayName("Should get department details when asked given Id")
    public void shouldReturnDepartmentDetails() throws DepartmentException {
        //Given data from db.changelog-01-t-department.yaml
        Department expectedDepartment = Department.builder().Id(1L).departmentName("CS").hodName("Paul Williams").build();
        //When
        Department department = departmentRepository.fetchDepartmentById(1L);
        //Then
        assertThat(department)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expectedDepartment);
    }

    @Test
    @DisplayName("Should throw exception when invalid id is given")
    public void shouldThrowExceptionWhenInvalidIdProvided() throws DepartmentException {
        //Given data from db.changelog-01-t-department.yaml
        //When and Then
        assertThatThrownBy(() -> departmentRepository.fetchDepartmentById(20L)).isInstanceOf(DepartmentException.class)
                .hasMessageContaining("No record found for the given department id");
    }
}
