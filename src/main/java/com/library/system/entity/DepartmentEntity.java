package com.library.system.entity;

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
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_DEPARTMENT")
  @SequenceGenerator(
      name = "SEQ_T_DEPARTMENT",
      sequenceName = "SEQ_T_DEPARTMENT",
      initialValue = 1,
      allocationSize = 1)
  @Column(name = "ID")
  private Long Id;

  @Column(name = "DEPARTMENT_NAME")
  private String departmentName;

  @Column(name = "HOD_NAME")
  private String hodName;

  /*@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID") //first is the foreign from the other table, second is that field from current table
  List<StudentEntity> studentEntityList;*/

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
