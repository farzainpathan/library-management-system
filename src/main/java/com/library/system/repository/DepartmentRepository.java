package com.library.system.repository;

import com.library.system.dao.DepartmentDao;
import com.library.system.domian.Department;
import com.library.system.entity.DepartmentEntity;
import com.library.system.exception.DepartmentNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DepartmentRepository implements DepartmentPersistence {

  private final DepartmentDao departmentDao;

  public DepartmentRepository(DepartmentDao departmentDao) {
    this.departmentDao = departmentDao;
  }

  @Override
  public Department saveDepartment(Department department) {
    DepartmentEntity departmentEntity =
        departmentDao.save(DepartmentEntity.createEntity(department));
    return DepartmentEntity.toModel(departmentEntity);
  }

  @Override
  public List<Department> fetchAllDepartments() {
    List<DepartmentEntity> departmentEntities = departmentDao.findAll();
    return DepartmentEntity.toModel(departmentEntities);
  }

  @Override
  public Optional<Department> fetchDepartmentById(Long departmentId)
      throws DepartmentNotFoundException {
    Optional<DepartmentEntity> departmentEntity = departmentDao.findById(departmentId);
    if (departmentEntity.isPresent())
      return Optional.of(DepartmentEntity.toModel(departmentEntity.get()));
    else throw new DepartmentNotFoundException("No record found for the given department id");
  }

  @Override
  public void deleteById(Long departmentId) {
    departmentDao.deleteById(departmentId);
  }
}
