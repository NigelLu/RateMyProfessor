package edu.nyu.ratemyprofessor.objects.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.nyu.ratemyprofessor.objects.models.SavedProfessor;

public interface SavedProfessorRepository extends JpaRepository<SavedProfessor, Long>{
    List<SavedProfessor> findByStudentId(Long studentId);

    Optional<SavedProfessor> findByStudentIdAndProfessorId(Long studentId, 
                                                        Long professorId);

    void deleteByStudentIdAndProfessorId(Long studentId,
                                            Long professorId);
                                            
}
