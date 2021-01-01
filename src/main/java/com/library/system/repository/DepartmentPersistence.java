package com.library.system.repository;

import com.library.system.domian.Department;
import com.library.system.exception.DepartmentException;

import java.util.List;
import java.util.Optional;

public interface DepartmentPersistence {

    Optional<Department> saveDepartment(Department department);

    Optional<List<Department>> fetchAllCourses() throws DepartmentException;

    Department fetchDepartmentById(Long departmentId) throws DepartmentException;
}
