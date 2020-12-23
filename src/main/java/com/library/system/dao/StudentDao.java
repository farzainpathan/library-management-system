package com.library.system.dao;

import com.library.system.Entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<StudentEntity, Long> {
}
