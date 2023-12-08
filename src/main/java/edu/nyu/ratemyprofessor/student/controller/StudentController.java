package edu.nyu.ratemyprofessor.student.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.json.JSONObject;

import edu.nyu.ratemyprofessor.objects.models.School;
import edu.nyu.ratemyprofessor.objects.services.interfaces.SchoolService;
import edu.nyu.ratemyprofessor.student.model.Student;
import edu.nyu.ratemyprofessor.student.model.pojos.StudentLoginPOJO;
import edu.nyu.ratemyprofessor.student.model.pojos.StudentRegisterPOJO;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/student")
public class StudentController {
  private final SchoolService schoolService;
  private final StudentService studentService;

  @Autowired
  public StudentController(StudentService studentService, SchoolService schoolService) {
    this.schoolService = schoolService;
    this.studentService = studentService;
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody StudentLoginPOJO studentLoginPOJO) {
    boolean isAuthenticated = studentService.authenticateStudent(studentLoginPOJO.getEmail(),
        studentLoginPOJO.getPassword());
    Student student;
    try {
      student = studentService.getStudentDetailByEmail(studentLoginPOJO.getEmail()).get();
    } catch (NoSuchElementException e) {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("error", "Your email is not registered with us");
      return ResponseEntity.badRequest().body(jsonObject.toString());
    }

    if (isAuthenticated) {
      return ResponseEntity.ok().body(Student.toJsonString(student));
    } else {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("error", "Invalid email or password");
      return ResponseEntity.badRequest().body(jsonObject.toString());
    }
  }

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody StudentRegisterPOJO studentRegisterPOJO) {
    School school;
    try {
      school = schoolService.getSchoolById(studentRegisterPOJO.getSchoolId());
    } catch (EntityNotFoundException e) {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("error", String.format("School with id %d cannot be found", studentRegisterPOJO.getSchoolId()));
      return ResponseEntity.badRequest().body(jsonObject.toString());
    }
    if (!studentRegisterPOJO.hasAllRequiredFields()) {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("error", "Missing required fields on the register form you submitted");
      return ResponseEntity.badRequest().body(jsonObject.toString());
    }
    Student newStudent = new Student(studentRegisterPOJO.getEmail(),
        studentRegisterPOJO.getLastName(), studentRegisterPOJO.getPassword(), studentRegisterPOJO.getFirstName(),
        studentRegisterPOJO.getSchoolId());
    newStudent.setSchool(school);
    try {
      newStudent = studentService.addNewStudent(newStudent);
    } catch (Exception e) {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("error", "Failed to save your registration info, please retry or contact site admin");
      return ResponseEntity.badRequest().body(jsonObject.toString());
    }
    return ResponseEntity.ok().body(Student.toJsonString(newStudent));
  }
}
