package com.library.system.dao;

import com.library.system.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends JpaRepository<StudentEntity, Long> {

    //StudentEntity updateStudent(StudentEntity student);
}
