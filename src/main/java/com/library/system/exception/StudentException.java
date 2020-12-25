package com.library.system.exception;

import com.library.system.domian.Student;

public class StudentException extends Exception {

    public StudentException(Student student) {
        super("Invalid USN | No Student is associated with the given USN : ");
    }

    public StudentException(String message) {
        super(message);
    }
}
