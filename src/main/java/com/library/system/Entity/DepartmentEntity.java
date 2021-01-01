package com.library.system.Entity;

import com.library.system.domian.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "T_DEPARTMENT")
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long Id;

    @Column(name = "DEPARTMENT_NAME")
    private String departmentName;

    @Column(name = "HOD_NAME")
    private String hodName;

    public static List<Department> toModel(List<DepartmentEntity> departmentEntities) {
        return departmentEntities.stream().map(DepartmentEntity::toModel).collect(Collectors.toList());
    }

    public static Department toModel(DepartmentEntity departmentEntity) {
        return Department.builder()
                .Id(departmentEntity.getId())
                .departmentName(departmentEntity.getDepartmentName())
                .hodName(departmentEntity.getHodName())
                .build();
    }

    public static DepartmentEntity createEntity(Department department) {
        return DepartmentEntity.builder()
                .Id(department.getId())
                .departmentName(department.getDepartmentName())
                .hodName(department.getHodName())
                .build();
    }
}
