package com.library.system.dao;

import com.library.system.Entity.AuthorEntity;
import com.library.system.Entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorDao extends JpaRepository<AuthorEntity, Long> {
}
