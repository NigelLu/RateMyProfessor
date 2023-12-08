package edu.nyu.ratemyprofessor.student.controller;

import javax.persistence.EntityNotFoundException;

import edu.nyu.ratemyprofessor.student.model.Student;

public interface StudentService {
  Student getStudentDetailById(Long studentId) throws EntityNotFoundException;

  Student getStudentDetailByEmail(String email) throws EntityNotFoundException;

  Student addNewStudent(Student student) throws Exception;

  Student updateStudent(Student student) throws Exception;

  boolean authenticateStudent(String email, String password);
}
