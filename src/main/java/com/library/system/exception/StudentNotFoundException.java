package com.library.system.exception;

import com.library.system.domian.Student;

public class StudentNotFoundException extends Exception {

  public StudentNotFoundException(Student student) {
    super("Invalid USN | No Student is associated with the given USN : ");
  }

  public StudentNotFoundException(String message) {
    super(message);
  }
}
