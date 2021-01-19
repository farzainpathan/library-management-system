package com.library.system.exception;

import com.library.system.domian.Department;

public class DepartmentNotFoundException extends Exception {

    public DepartmentNotFoundException(Department department) {
        super("Invalid Course Id | No department exist");
    }

    public DepartmentNotFoundException(String msg) {
        super(msg);
    }
}
