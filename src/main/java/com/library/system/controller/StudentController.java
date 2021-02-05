package com.library.system.controller;

import com.library.system.domian.Student;
import com.library.system.exception.StudentNotFoundException;
import com.library.system.service.RequestStudent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
@Api(value = "Student Endpoints")
public class StudentController {

  private final RequestStudent requestStudent;

  @Autowired
  public StudentController(RequestStudent requestStudent) {
    this.requestStudent = requestStudent;
  }

  @GetMapping("/students")
  @ApiOperation(value = "This endpoint fetches all the students from database")
  public Optional<List<Student>> getAllStudent() throws StudentNotFoundException {
    return requestStudent.fetchAllStudents();
  }
}
