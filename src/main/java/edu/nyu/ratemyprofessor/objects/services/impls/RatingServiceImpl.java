package edu.nyu.ratemyprofessor.objects.services.impls;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nyu.ratemyprofessor.objects.models.Rating;
import edu.nyu.ratemyprofessor.objects.repos.RatingRepository;
import edu.nyu.ratemyprofessor.objects.services.interfaces.RatingService;
import edu.nyu.ratemyprofessor.professor.model.Professor;
import edu.nyu.ratemyprofessor.professor.repo.ProfessorRepository;
import edu.nyu.ratemyprofessor.student.model.Student;
import edu.nyu.ratemyprofessor.student.repository.StudentRepository;

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
    public Rating addRating(Rating rating) {

        Long studentId = rating.getStudentId();
        Long professorId = rating.getProfessorId();

        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Optional<Professor> professorOptional = professorRepository.findById(professorId);

        Student student = studentOptional
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + rating.getStudentId()));
        Professor professor = professorOptional.orElseThrow(
                () -> new EntityNotFoundException("Professor not found with id: " + rating.getProfessorId()));

        rating.setStudent(student);
        rating.setProfessor(professor);
        rating.setDateTime(LocalDateTime.now());
        ratingRepository.save(rating);

        return rating;
    }

    @Override
    public Rating findRating(Long id) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rating Not found"));
        return rating;
    }

    @Transactional
    @Override
    public Rating editRating(Rating rating) {
        Rating thisRating = ratingRepository.findById(rating.getId())
                .orElseThrow(() -> new EntityNotFoundException("Rating Not found"));

        if (!thisRating.getStudentId().equals(rating.getStudentId())) {
            throw new IllegalStateException("Student id incompatible.");
        }

        thisRating.setRating(rating.getRating());
        thisRating.setDifficulty(rating.getDifficulty());
        thisRating.setTakeAgain(rating.isTakeAgain());
        thisRating.setTakenForCredit(rating.isTakenForCredit());
        thisRating.setAttendanceMandatory(rating.isAttendanceMandatory());
        thisRating.setGrade(rating.getGrade());
        thisRating.setReview(rating.getReview());
        thisRating.setDateTime(LocalDateTime.now());

        return thisRating;
    }
}
