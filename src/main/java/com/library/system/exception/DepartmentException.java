package com.library.system.exception;

import com.library.system.domian.Department;

public class DepartmentException extends Exception {

    public DepartmentException(Department department) {
        super("Invalid Course Id | No department exist");
    }

    public DepartmentException(String msg) {
        super(msg);
    }
}
