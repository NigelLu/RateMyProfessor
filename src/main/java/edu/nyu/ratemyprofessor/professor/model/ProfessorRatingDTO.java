package edu.nyu.ratemyprofessor.professor.model;

import java.util.List;

import edu.nyu.ratemyprofessor.objects.dtos.RatingDTO;

// This model will return all rating details of the professor.
public class ProfessorRatingDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String schoolName;
    private String departmentName;
    private Long schoolId;
    private List<RatingDTO> ratingDTOList;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getSchoolName() {
        return schoolName;
    }
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public Long getSchoolId() {
        return schoolId;
    }
    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }
    public List<RatingDTO> getRatingDTOList() {
        return ratingDTOList;
    }
    public void setRatingDTOList(List<RatingDTO> ratingDTOList) {
        this.ratingDTOList = ratingDTOList;
    }
}
