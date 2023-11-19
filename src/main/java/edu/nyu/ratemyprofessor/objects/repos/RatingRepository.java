package edu.nyu.ratemyprofessor.objects.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.nyu.ratemyprofessor.objects.models.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long>{
    List<Rating> findByProfessorId(Long professorId);
}
