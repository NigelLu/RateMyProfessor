package edu.nyu.ratemyprofessor.objects.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import edu.nyu.ratemyprofessor.objects.dtos.RatingDTO;
import edu.nyu.ratemyprofessor.professor.model.Professor;
import edu.nyu.ratemyprofessor.student.model.Student;
import edu.nyu.ratemyprofessor.utils.Grade;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
public class Rating {

    @Id
    @SequenceGenerator(name = "ratingSequence", sequenceName = "ratingSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ratingSequence")
    private Long id;

    private Float rating;

    private Float difficulty;

    private boolean takeAgain;

    private boolean takenForCredit;

    private boolean attendanceMandatory;

    private Grade grade;

    private String review;

    private Long professorId;

    private Long studentId;

    private LocalDateTime dateTime;

    public Rating(Float rating, Float difficulty, boolean takeAgain, boolean takenForCredit,
            boolean attendanceMandatory, Grade grade, String review, Long professorId, Long studentId,
            LocalDateTime dateTime, Professor professor, Student student) {

        this.rating = rating;
        this.difficulty = difficulty;
        this.takeAgain = takeAgain;
        this.takenForCredit = takenForCredit;
        this.attendanceMandatory = attendanceMandatory;
        this.grade = grade;
        this.review = review;
        this.professorId = professorId;
        this.studentId = studentId;
        this.dateTime = dateTime;
        this.professor = professor;
        this.student = student;
    }

    @ManyToOne
    @JoinColumn(name = "professor")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "student")
    private Student student;

    public static RatingDTO toRatingDTO(Rating rating) {
        RatingDTO dto = new RatingDTO();
        dto.setId(rating.getId());
        dto.setRating(rating.getRating());
        dto.setDifficulty(rating.getDifficulty());
        dto.setTakeAgain(rating.isTakeAgain());
        dto.setTakenForCredit(rating.isTakenForCredit());
        dto.setAttendanceMandatory(rating.isAttendanceMandatory());
        dto.setGrade(rating.getGrade());
        dto.setProfessorId(rating.getProfessorId());
        dto.setStudentId(rating.getStudentId());
        dto.setReview(rating.getReview());
        dto.setDateTime(rating.getDateTime());
        dto.setStudentId(rating.getStudentId());
        dto.setProfessorFirstName(rating.getProfessor().getFirstName());
        dto.setProfessorLastName(rating.getProfessor().getLastName());
        dto.setProfessorSchoolName(rating.getProfessor().getSchoolName());

        return dto;
    }

}
