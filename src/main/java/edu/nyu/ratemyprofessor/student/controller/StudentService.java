package edu.nyu.ratemyprofessor.student.controller;

import java.util.Optional;

import edu.nyu.ratemyprofessor.student.model.Student;

public interface StudentService {
  Optional<Student> getStudentDetailById(Long studentId);

  Optional<Student> getStudentDetailByEmail(String email);

  Student addNewStudent(Student student) throws Exception;

  boolean authenticateStudent(String email, String password);
}
