package edu.nyu.ratemyprofessor.student.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.nyu.ratemyprofessor.student.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findById(Long studentId);

    Optional<Student> findByEmail(String email);
}
