package com.library.system.domian;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private Long Id;

    private String firstName;

    private String lastName;

    private String usn;

    private Department department;
}
