package edu.nyu.ratemyprofessor.objects.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.nyu.ratemyprofessor.objects.models.Saving;

public interface SavingRepository extends JpaRepository<Saving, Long>{
        
}
