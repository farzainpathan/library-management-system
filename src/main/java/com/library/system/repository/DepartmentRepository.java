package com.library.system.repository;

import com.library.system.entity.DepartmentEntity;
import com.library.system.dao.DepartmentDao;
import com.library.system.domian.Department;
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
        //TODO: remove the optional in the save. -> DONE
        //TODO: handle for create and update by adding a check to see if ID exist or not, or else then throw an exception
        DepartmentEntity departmentEntity = departmentDao.save(DepartmentEntity.createEntity(department));
        return DepartmentEntity.toModel(departmentEntity);
    }

    @Override
    public List<Department> fetchAllDepartments() {
        //TODO: return type should not be Optional and return empty array, do not throw any exception here -> DONE
        //TODO: return empty array not throw Exception -> DONE
        List<DepartmentEntity> departmentEntities = departmentDao.findAll();
        return DepartmentEntity.toModel(departmentEntities);
    }

    @Override
    public Optional<Department> fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<DepartmentEntity> departmentEntity = departmentDao.findById(departmentId);
        //TODO: refactor Optional.isPresent().thenThrow()
        //TODO: DepartmentException should be DepartmentNotFoundException -> DONE
        if (departmentEntity.isPresent())
            return Optional.of(DepartmentEntity.toModel(departmentEntity.get()));
        else
            throw new DepartmentNotFoundException("No record found for the given department id");
    }

    @Override
    public void deleteById(Long departmentId) {
        departmentDao.deleteById(departmentId);
    }
}
