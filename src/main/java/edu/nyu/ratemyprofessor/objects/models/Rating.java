package edu.nyu.ratemyprofessor.objects.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import edu.nyu.ratemyprofessor.objects.dtos.RatingDTO;
import edu.nyu.ratemyprofessor.professor.model.Professor;
import edu.nyu.ratemyprofessor.user.model.Student;
import edu.nyu.ratemyprofessor.utils.Grade;

@Table
@Entity
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

    private Long userId;

    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "professor")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "student")
    private Student student;

    public Rating() {
    }

    public Rating(Float rating, Float difficulty, boolean takeAgain, boolean takenForCredit,
            boolean attendanceMandatory, Grade grade, String review, Long professorId, Long userId) {
        this.rating = rating;
        this.difficulty = difficulty;
        this.takeAgain = takeAgain;
        this.takenForCredit = takenForCredit;
        this.attendanceMandatory = attendanceMandatory;
        this.grade = grade;
        this.review = review;
        this.professorId = professorId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Float getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Float difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isTakeAgain() {
        return takeAgain;
    }

    public void setTakeAgain(boolean takeAgain) {
        this.takeAgain = takeAgain;
    }

    public boolean isTakenForCredit() {
        return takenForCredit;
    }

    public void setTakenForCredit(boolean takenForCredit) {
        this.takenForCredit = takenForCredit;
    }

    public boolean isAttendanceMandatory() {
        return attendanceMandatory;
    }

    public void setAttendanceMandatory(boolean attendanceMandatory) {
        this.attendanceMandatory = attendanceMandatory;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public static RatingDTO toRatingDTO(Rating rating) {
        RatingDTO dto = new RatingDTO();
        dto.setId(rating.getId());
        dto.setDifficulty(rating.getDifficulty());
        dto.setTakeAgain(rating.isTakeAgain());
        dto.setTakenForCredit(rating.isTakenForCredit());
        dto.setAttendanceMandatory(rating.isAttendanceMandatory());
        dto.setGrade(rating.getGrade());
        dto.setProfessorId(rating.getProfessorId());
        dto.setUserId(rating.getUserId());
        dto.setReview(rating.getReview());
        dto.setDateTime(rating.getDateTime());
        return dto;
    }

}
