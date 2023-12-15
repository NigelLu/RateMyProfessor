package edu.nyu.ratemyprofessor.student.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

import org.json.JSONObject;

import edu.nyu.ratemyprofessor.objects.models.School;
import edu.nyu.ratemyprofessor.student.model.Student;
import edu.nyu.ratemyprofessor.student.model.pojos.StudentLoginPOJO;
import edu.nyu.ratemyprofessor.student.model.pojos.StudentUpdatePOJO;
import edu.nyu.ratemyprofessor.student.model.pojos.StudentRegisterPOJO;
import edu.nyu.ratemyprofessor.objects.services.interfaces.SchoolService;

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
      student = studentService.getStudentDetailByEmail(studentLoginPOJO.getEmail());
    } catch (EntityNotFoundException e) {
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

    if (!studentRegisterPOJO.hasAllRequiredFields()) {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("error", "Missing required fields on the register form you submitted");
      return ResponseEntity.badRequest().body(jsonObject.toString());
    }
    School school;
    try {
      school = schoolService.getSchoolById(studentRegisterPOJO.getSchoolId());
    } catch (EntityNotFoundException e) {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("error", String.format("School with id %d cannot be found", studentRegisterPOJO.getSchoolId()));
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

  @PostMapping("/update")
  public ResponseEntity<String> update(@RequestBody StudentUpdatePOJO studentUpdatePOJO) {

    if (!studentUpdatePOJO.hasAllRequiredFields()) {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("error", "Missing required fields on the profile update form you submitted");
      return ResponseEntity.badRequest().body(jsonObject.toString());
    }
    School school;
    try {
      school = schoolService.getSchoolById(studentUpdatePOJO.getSchoolId());
    } catch (EntityNotFoundException e) {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("error", String.format("School with id %d cannot be found", studentUpdatePOJO.getSchoolId()));
      return ResponseEntity.badRequest().body(jsonObject.toString());
    }

    Student student;
    try {
      student = studentService.getStudentDetailById(studentUpdatePOJO.getId());
    } catch (EntityNotFoundException e) {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("error", String.format("Student with id %d cannot be found", studentUpdatePOJO.getId()));
      return ResponseEntity.badRequest().body(jsonObject.toString());
    }

    try {
      student.setSchool(school);
      student.setEmail(studentUpdatePOJO.getEmail());
      student.setSchoolId(studentUpdatePOJO.getSchoolId());
      student.setLastName(studentUpdatePOJO.getLastName());
      student.setFirstName(studentUpdatePOJO.getFirstName());
      student.setExpectedYearOfGraduation(studentUpdatePOJO.getExpectedYearOfGraduation());
      studentService.updateStudent(student);
    } catch (Exception e) {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("error", "Failed to save your new profile info, please retry or contact site admin");
      return ResponseEntity.badRequest().body(jsonObject.toString());
    }
    return ResponseEntity.ok().body(Student.toJsonString(student));
  }

  // @GetMapping("/ratings/{studentId}")
  // public ResponseEntity<String> ratings(@PathVariable("studentId") Long studentId) {
  //   Student student;
  //   try {
  //     student = studentService.getStudentDetailById(studentId);
  //   } catch (EntityNotFoundException e) {
  //     JSONObject jsonObject = new JSONObject();
  //     jsonObject.put("error", String.format("Student with id %d cannot be found", studentId));
  //     return ResponseEntity.badRequest().body(jsonObject.toString());
  //   }

  //   return ResponseEntity.ok().body(Student.toJsonString(student));
  // }
}
