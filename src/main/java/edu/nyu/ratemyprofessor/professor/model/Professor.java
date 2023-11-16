package edu.nyu.ratemyprofessor.professor.model;

import edu.nyu.ratemyprofessor.objects.models.School;
import javax.persistence.*;

@Entity
@Table
public class Professor {
    @Id
    @SequenceGenerator(name = "professorSequence", sequenceName = "professorSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "professorSequence")
    private Long id;

    private String firstName;

    private String lastName;

    private Long schoolId;

    private String schoolName;

    private String departmentName;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "school", nullable = false)
    private School school;

    public Professor() {

    }

    public Professor(String firstName, String lastName, String schoolName, String departmentName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.schoolName = schoolName;
        this.departmentName = departmentName;
    }

    public Professor(String firstName, String lastName, String schoolName, String departmentName, Long schoolId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.schoolName = schoolName;
        this.departmentName = departmentName;
        this.schoolId = schoolId;
    }

    public Professor(Long id, String firstName, String lastName, String schoolName, String departmentName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.schoolName = schoolName;
        this.departmentName = departmentName;
    }

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

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
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

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public static ProfessorDTO toProfessorDTO(Professor professor) {
        ProfessorDTO dto = new ProfessorDTO();
        dto.setId(professor.getId());
        dto.setFirstName(professor.getFirstName());
        dto.setLastName(professor.getLastName());
        dto.setDepartmentName(professor.getDepartmentName());
        dto.setSchoolId(professor.getSchoolId());
        dto.setSchoolName(professor.getSchoolName());
        return dto;
    }
}