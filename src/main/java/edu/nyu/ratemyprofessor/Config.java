package edu.nyu.ratemyprofessor;

import edu.nyu.ratemyprofessor.objects.models.School;
import edu.nyu.ratemyprofessor.objects.repos.SchoolRepository;
import edu.nyu.ratemyprofessor.user.model.Student;
import edu.nyu.ratemyprofessor.user.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import javax.persistence.EntityNotFoundException;

@Configuration
public class Config {

    private final SchoolRepository schoolRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public Config(SchoolRepository schoolRepository, StudentRepository studentRepository) {
        this.schoolRepository = schoolRepository;
        this.studentRepository = studentRepository;
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

                Long id = 1L;
                NYU = schoolRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

                Student student = new Student(
                    "pw1298@nyu.edu",
                    "Weng",
                    "123456",
                    "Jay",
                    id
                );

                student.setSchool(NYU);

                studentRepository.save(student);
            }
        };
    }



}
