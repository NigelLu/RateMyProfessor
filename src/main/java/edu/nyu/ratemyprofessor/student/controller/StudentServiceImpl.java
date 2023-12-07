package edu.nyu.ratemyprofessor.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import edu.nyu.ratemyprofessor.student.model.Student;
import edu.nyu.ratemyprofessor.objects.models.School;
import edu.nyu.ratemyprofessor.objects.repos.SchoolRepository;
// import edu.nyu.ratemyprofessor.security.StudentAuthorities;
import edu.nyu.ratemyprofessor.student.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
  private final PasswordEncoder passwordEncoder;
  private final SchoolRepository schoolRepository;
  private final StudentRepository studentRepository;

  @Autowired
  public StudentServiceImpl(StudentRepository studentRepository, PasswordEncoder passwordEncoder,
      SchoolRepository schoolRepository) {
    this.passwordEncoder = passwordEncoder;
    this.schoolRepository = schoolRepository;
    this.studentRepository = studentRepository;
  }

  @Override
  public Optional<Student> getStudentDetailById(Long studentId) {
    return this.studentRepository.findById(studentId);
  }

  @Override
  public Optional<Student> getStudentDetailByEmail(String email) {
    return this.studentRepository.findByEmail(email);
  }

  @Override
  public Student addNewStudent(Student student) throws Exception {
    // * check if email, password, and school ID are supplied
    if (student.getEmail().isEmpty() || student.getPassword().isEmpty() || student.getSchoolId() == null)
      throw new Exception("Email or Password or SchoolId cannot be empty");

    // * check for duplicated emails
    if (this.studentRepository.findByEmail(student.getEmail()).isPresent())
      throw new Exception("The email you use to register already exists in our system");

    // * validate school ID
    School foundSchool = this.schoolRepository.findById(student.getSchoolId()).orElseThrow(
        () -> new EntityNotFoundException(
            String.format("School with ID %s could not be found", student.getSchoolId().toString())));
    student.setSchool(foundSchool);

    // * encrypt password
    String rawPwd = student.getPassword();
    String encodedPwd = this.passwordEncoder.encode(rawPwd);
    student.setPassword(encodedPwd);
    this.studentRepository.save(student);

    return student;
  }

  @Override
  public boolean authenticateStudent(String email, String password) {
    Optional<Student> studentOptional = this.getStudentDetailByEmail(email);
    if (!studentOptional.isPresent())
      return false;

    Student student = studentOptional.get();
    return this.passwordEncoder.matches(password, student.getPassword());
  }

  // @Override
  // public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
  //   Student student = this.studentRepository.findByEmail(email)
  //       .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with email %s", email)));

  //   return new User(student.getEmail(), student.getPassword(), StudentAuthorities.getAuthorities());
  // }
}
