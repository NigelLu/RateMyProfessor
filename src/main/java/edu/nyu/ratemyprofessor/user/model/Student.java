package edu.nyu.ratemyprofessor.user.model;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.validation.constraints.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import edu.nyu.ratemyprofessor.objects.dtos.RatingDTO;
import edu.nyu.ratemyprofessor.objects.dtos.SavedProfessorDTO;
import edu.nyu.ratemyprofessor.objects.models.*;
import edu.nyu.ratemyprofessor.user.model.dtos.StudentRatingDTO;
import edu.nyu.ratemyprofessor.user.model.dtos.StudentSavedProfessorDTO;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    @Email(message = "User email is invalid")
    private String email;

    @NonNull
    @NotBlank
    @Column(nullable = false)
    private String lastName;

    @NonNull
    @NotBlank
    @Column(nullable = false)
    private String password;

    @NonNull
    @NotBlank
    @Column(nullable = false)
    private String firstName;

    private Number expectedYearOfGraduation;

    private Long schoolId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "school", nullable = false)
    private School school;

    @OneToMany(mappedBy = "student")
    private List<Rating> ratinglList;

    @OneToMany(mappedBy = "student")
    private List<SavedProfessor> savedProfessorList;
    
    public Student() {
    }

    public Student(@NonNull @Email(message = "User email is invalid") String email, @NonNull @NotBlank String lastName,
            @NonNull @NotBlank String password, @NonNull @NotBlank String firstName, Long schoolId) {
        this.email = email;
        this.lastName = lastName;
        this.password = password;
        this.firstName = firstName;
        this.schoolId = schoolId;
    }

    public static StudentDTO toStudentDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setEmail(student.getEmail());
        dto.setExpectedYearOfGraduation(student.getExpectedYearOfGraduation());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setPassword(student.getPassword());
        return dto;
    } 

    public static StudentRatingDTO toStudentRatingDTO(Student student) {
        StudentRatingDTO dto = new StudentRatingDTO();
        dto.setId(student.getId());
        dto.setEmail(student.getEmail());
        dto.setExpectedYearOfGraduation(student.getExpectedYearOfGraduation());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setPassword(student.getPassword());
        
        List<RatingDTO> ratingDTOList = student.getRatinglList().stream()
                                                                .map(Rating::toRatingDTO)
                                                                .collect(Collectors.toList());

        dto.setRatingDTOList(ratingDTOList);
        return dto;
    }

    public static StudentSavedProfessorDTO toStudentSavedProfessorDTO(Student student) {
        StudentSavedProfessorDTO dto = new StudentSavedProfessorDTO();
        dto.setId(student.getId());
        dto.setEmail(student.getEmail());
        dto.setExpectedYearOfGraduation(student.getExpectedYearOfGraduation());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setPassword(student.getPassword());

        List<SavedProfessorDTO> savedProfessorDTOList = student.getSavedProfessorList().stream()
                                                                .map(SavedProfessor::toSavedProfessorDTO)
                                                                .collect(Collectors.toList());

        dto.setSavedProfessorDTOList(savedProfessorDTOList);
        return dto;
    }
}
