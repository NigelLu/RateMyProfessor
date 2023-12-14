package edu.nyu.ratemyprofessor.objects.dtos;

import java.time.LocalDateTime;

import edu.nyu.ratemyprofessor.utils.Grade;

public class RatingDTO {
    private Long id;

    private Float rating;

    private Float difficulty;

    private boolean takeAgain;

    private boolean takenForCredit;

    private boolean attendanceMandatory;

    private Grade grade;

    private String review;

    private Long professorId;

    private String professorFirstName;

    private String professorLastName;

    private String professorSchoolName;

    private Long studentId;

    private LocalDateTime dateTime;

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

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean getTakeAgain() {
        return this.takeAgain;
    }


    public boolean getTakenForCredit() {
        return this.takenForCredit;
    }


    public boolean getAttendanceMandatory() {
        return this.attendanceMandatory;
    }


    public String getProfessorFirstName() {
        return this.professorFirstName;
    }

    public void setProfessorFirstName(String professorFirstName) {
        this.professorFirstName = professorFirstName;
    }

    public String getProfessorLastName() {
        return this.professorLastName;
    }

    public void setProfessorLastName(String professorLastName) {
        this.professorLastName = professorLastName;
    }

    public String getProfessorSchoolName() {
        return this.professorSchoolName;
    }

    public void setProfessorSchoolName(String professorSchoolName) {
        this.professorSchoolName = professorSchoolName;
    }

}
