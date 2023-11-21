package edu.nyu.ratemyprofessor.objects.services.impls;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nyu.ratemyprofessor.objects.models.Rating;
import edu.nyu.ratemyprofessor.objects.repos.RatingRepository;
import edu.nyu.ratemyprofessor.objects.services.interfaces.RatingService;
import edu.nyu.ratemyprofessor.professor.model.Professor;
import edu.nyu.ratemyprofessor.professor.repo.ProfessorRepository;
import edu.nyu.ratemyprofessor.user.model.Student;
import edu.nyu.ratemyprofessor.user.repository.StudentRepository;


@Service
public class RatingServiceImpl implements RatingService {
    
    private final RatingRepository ratingRepository;
    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository, StudentRepository studentRepository,
            ProfessorRepository professorRepository) {
        this.ratingRepository = ratingRepository;
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
    }

    @Override
    public Rating addRating(Rating rating) throws EntityNotFoundException{

        Long studentId = rating.getStudentId();
        Long professorId = rating.getProfessorId();
        
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Optional<Professor> professorOptional = professorRepository.findById(professorId);

        Student student = studentOptional.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + rating.getStudentId()));
        Professor professor = professorOptional.orElseThrow(() -> new EntityNotFoundException("Professor not found with id: " + rating.getProfessorId()));

        rating.setStudent(student);
        rating.setProfessor(professor);
        rating.setDateTime(LocalDateTime.now());
        ratingRepository.save(rating);

        return rating;
    }
}
