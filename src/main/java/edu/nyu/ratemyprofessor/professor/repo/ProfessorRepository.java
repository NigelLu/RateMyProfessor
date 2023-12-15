package edu.nyu.ratemyprofessor.professor.repo;

import edu.nyu.ratemyprofessor.professor.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Optional<Professor> findById(Long professorId);

    List<Professor> findBySchoolId(Long schoolId);

    @Query("SELECT p FROM Professor p WHERE p.schoolId = :schoolId AND (LOWER(p.firstName) LIKE %:firstName% OR LOWER(p.lastName) LIKE %:lastName%)")
    List<Professor> findBySchoolIdAndFirstNameContainingOrLastNameContaining(
            @Param("schoolId") Long schoolId,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName);

    Optional<Professor> findByFirstNameAndLastNameAndSchoolNameAndDepartmentName(String firstName,
            String lastName,
            String schoolName,
            String departmentName);
}
