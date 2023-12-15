package edu.nyu.ratemyprofessor.objects.repos;

import edu.nyu.ratemyprofessor.objects.models.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School, Long> {
    Optional<School> findById(Long id);

    Optional<School> findByName(String name);

}
