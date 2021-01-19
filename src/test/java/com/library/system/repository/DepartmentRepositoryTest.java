package com.library.system.repository;

import com.library.system.bootstrap.LibraryManagementSystemApplication;
import com.library.system.domian.Department;
import com.library.system.exception.DepartmentNotFoundException;
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
    @DisplayName("Should fetch all the departments when asked from database")
    public void shouldFetchAllTheDepartments() {
        //Given data from db.changelog-01-t-department.yaml
        //When
        List<Department> departmentList = departmentRepository.fetchAllDepartments();
        //Then
        System.out.println("======== " + departmentList);
        assertThat(departmentList)
                .isNotNull()
                .hasSizeGreaterThanOrEqualTo(3)
                .extracting("departmentName", "hodName")
                .contains(
                        tuple("CS", "Paul Williams"),
                        tuple("IS", "Sandeep Shinde"),
                        tuple("MCA", "Don Eluvathingal")
                );
    }

    @Test
    @DisplayName("Should get department details when asked given Id")
    public void shouldReturnDepartmentDetails() throws DepartmentNotFoundException {
        //Given data from db.changelog-01-t-department.yaml
        //When
        //TODO: Do not had code Id here, check how to do it using Sequence
        Optional<Department> department = departmentRepository.fetchDepartmentById(2L);
        //Then
        assertThat(department.get())
                .isNotNull()
                .extracting("departmentName", "hodName")
                .contains(
                        "IS", "Sandeep Shinde"
                );
    }

    @Test
    @DisplayName("Should throw exception when invalid id is given")
    public void shouldThrowExceptionWhenInvalidIdProvided() throws DepartmentNotFoundException {
        //Given data from db.changelog-01-t-department.yaml
        //When and Then
        assertThatThrownBy(() -> departmentRepository.fetchDepartmentById(20L)).isInstanceOf(DepartmentNotFoundException.class)
                .hasMessageContaining("No record found for the given department id");
    }

    @Test
    @DisplayName("Should save the department when asked")
    public void shouldSaveDepartment() {
        //Given data from db.changelog-01-t-department.yaml
        Department saveDepartment = Department.builder().departmentName("MBA").hodName("Narayana Swamy").build();
        //When
        Department savedDepartment = departmentRepository.saveDepartment(saveDepartment);
        //Then
        Department expectedDepartment = Department.builder().Id(4L).departmentName("MBA").hodName("Narayana Swamy").build();
        assertThat(savedDepartment).isNotNull().usingRecursiveComparison().isEqualTo(expectedDepartment);
    }

    //TODO: handle update
    //TODO: update with invalid ID, should throw exception
    //TODO: delete also
}
