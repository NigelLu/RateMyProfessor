package edu.nyu.ratemyprofessor.professor.repo;

import edu.nyu.ratemyprofessor.professor.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Optional<Professor> findById(Long professorId);

    List<Professor> findBySchoolId(Long schoolId);

    List<Professor> findBySchoolIdAndFirstNameContainingOrLastNameContaining(Long schoolId,
                                                                             String firstName,
                                                                             String lastName);

    Optional<Professor> findByFirstNameAndLastNameAndSchoolNameAndDepartmentName(String firstName,
                                                                                 String lastName,
                                                                                 String schoolName,
                                                                                 String departmentName);
}
