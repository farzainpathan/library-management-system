package com.library.system.entity;

import com.library.system.domian.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_STUDENT")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_STUDENT")
    @SequenceGenerator(name = "SEQ_T_STUDENT", sequenceName = "SEQ_T_STUDENT", initialValue = 1, allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "USN")
    private String usn;

    @ManyToOne(targetEntity = DepartmentEntity.class)
    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
    private DepartmentEntity departmentEntity;

    public static List<Student> toModel(List<StudentEntity> studentList) {
        return studentList.stream().map(StudentEntity::toModel).collect(Collectors.toList());
    }

    public static Student toModel(StudentEntity studentEntity) {
        return Student.builder()
                .Id(studentEntity.getId())
                .firstName(studentEntity.getFirstName())
                .lastName(studentEntity.getLastName())
                .usn(studentEntity.getUsn())
                .department(DepartmentEntity.toModel(studentEntity.getDepartmentEntity()))
                .build();
    }

    public static StudentEntity createEntity(Student student) {
        return StudentEntity.builder()
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .usn(student.getUsn())
                .departmentEntity(DepartmentEntity.createEntity(student.getDepartment()))
                .build();
    }
}
