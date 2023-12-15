package edu.nyu.ratemyprofessor.objects.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.nyu.ratemyprofessor.objects.models.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long>{
    List<Rating> findByProfessorId(Long professorId);

    Optional<Rating> findById(Long id);
}
