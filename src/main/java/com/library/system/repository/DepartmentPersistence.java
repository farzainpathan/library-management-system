package com.library.system.repository;

import com.library.system.domian.Department;
import com.library.system.exception.DepartmentNotFoundException;

import java.util.List;
import java.util.Optional;

public interface DepartmentPersistence {

  Department saveDepartment(Department department);

  List<Department> fetchAllDepartments();

  Optional<Department> fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException;

  void deleteById(Long departmentId);
}
