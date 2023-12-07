package edu.nyu.ratemyprofessor.student.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.json.JSONObject;
import edu.nyu.ratemyprofessor.student.model.Student;
import edu.nyu.ratemyprofessor.student.model.pojos.StudentLoginPOJO;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/student")
public class StudentController {
  private final StudentService studentService;

  @Autowired
  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody StudentLoginPOJO studentLoginPOJO) {
    boolean isAuthenticated = studentService.authenticateStudent(studentLoginPOJO.getEmail(),
        studentLoginPOJO.getPassword());
    Student student = studentService.getStudentDetailByEmail(studentLoginPOJO.getEmail()).get();

    if (isAuthenticated) {
      return ResponseEntity.ok().body(Student.toJsonString(student));
    } else {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("error", "Invalid email or password");
      return ResponseEntity.badRequest().body(jsonObject.toString());
    }
  }
}
