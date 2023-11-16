package edu.nyu.ratemyprofessor;

import edu.nyu.ratemyprofessor.objects.models.School;
import edu.nyu.ratemyprofessor.objects.repos.SchoolRepository;
import edu.nyu.ratemyprofessor.professor.repo.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Config {
    private final ProfessorRepository professorRepository;

    private final SchoolRepository schoolRepository;

    @Autowired
    public Config(ProfessorRepository professorRepository, SchoolRepository schoolRepository) {
        this.professorRepository = professorRepository;
        this.schoolRepository = schoolRepository;
    }

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            if (schoolRepository.findAll().isEmpty()) {
                School NYU = new School(
                        "New York University"
                );

                School CMU = new School(
                        "Carnegie Mellon University"
                );

                schoolRepository.saveAll(
                        List.of(NYU, CMU)
                );
            }
        };
    }

}
