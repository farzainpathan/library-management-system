package com.library.system.repository;

import com.library.system.Entity.DepartmentEntity;
import com.library.system.dao.DepartmentDao;
import com.library.system.domian.Department;
import com.library.system.exception.DepartmentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DepartmentRepository implements DepartmentPersistence {

    private final DepartmentDao departmentDao;

    @Autowired
    public DepartmentRepository(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public Optional<Department> saveDepartment(Department department) {
        DepartmentEntity departmentEntity = departmentDao.save(DepartmentEntity.createEntity(department));
        return Optional.of(DepartmentEntity.toModel(departmentEntity));
    }

    @Override
    public Optional<List<Department>> fetchAllCourses() throws DepartmentException {
        List<DepartmentEntity> departmentEntities = departmentDao.findAll();
        if (departmentEntities.isEmpty())
            throw new DepartmentException("No department records exists in the database");
        else
            return Optional.of(DepartmentEntity.toModel(departmentEntities));
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) throws DepartmentException {
        Optional<DepartmentEntity> departmentEntity = departmentDao.findById(departmentId);
        if (departmentEntity.isPresent())
            return DepartmentEntity.toModel(departmentEntity.get());
        else
            throw new DepartmentException("No record found for the given department id");
    }
}
