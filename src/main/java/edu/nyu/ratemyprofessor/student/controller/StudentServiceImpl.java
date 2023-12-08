package edu.nyu.ratemyprofessor.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import edu.nyu.ratemyprofessor.student.model.Student;
import edu.nyu.ratemyprofessor.student.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
  private final PasswordEncoder passwordEncoder;
  private final StudentRepository studentRepository;

  @Autowired
  public StudentServiceImpl(StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
    this.studentRepository = studentRepository;
  }

  @Override
  public Student getStudentDetailById(Long studentId) {
    return this.studentRepository.findById(studentId).orElseThrow(
        () -> new EntityNotFoundException(String.format("Student with id %d could not be found", studentId)));
  }

  @Override
  public Student getStudentDetailByEmail(String email) {
    return this.studentRepository.findByEmail(email).orElseThrow(
        () -> new EntityNotFoundException(String.format("Student with email %s could not be found", email)));
  }

  @Override
  public Student addNewStudent(Student student) throws Exception {
    // * check if email, password, and school ID are supplied
    if (student.getEmail().isEmpty() || student.getPassword().isEmpty() || student.getSchoolId() == null)
      throw new Exception("Email or Password or SchoolId cannot be empty");

    // * check for duplicated emails
    if (this.studentRepository.findByEmail(student.getEmail()).isPresent())
      throw new Exception("The email you use to register already exists in our system");

    // // * validate school ID
    // School foundSchool =
    // this.schoolRepository.findById(student.getSchoolId()).orElseThrow(
    // () -> new EntityNotFoundException(
    // String.format("School with ID %s could not be found",
    // student.getSchoolId().toString())));
    // student.setSchool(foundSchool);

    // * encrypt password
    String rawPwd = student.getPassword();
    String encodedPwd = this.passwordEncoder.encode(rawPwd);
    student.setPassword(encodedPwd);
    student = this.studentRepository.save(student);

    return student;
  }

  @Override
  public boolean authenticateStudent(String email, String password) {
    Student student;
    try {
      student = this.getStudentDetailByEmail(email);
    } catch (EntityNotFoundException e) {
      e.printStackTrace();
      return false;
    }
    return this.passwordEncoder.matches(password, student.getPassword());
  }

  @Override
  public Student updateStudent(Student student) throws Exception {
    return this.studentRepository.save(student);
  }
}
