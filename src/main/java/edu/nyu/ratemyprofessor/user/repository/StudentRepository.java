package edu.nyu.ratemyprofessor.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.nyu.ratemyprofessor.user.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
    Optional<Student> findById(Long studentId);
}
