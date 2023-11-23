package edu.nyu.ratemyprofessor.professor.model;

import edu.nyu.ratemyprofessor.objects.dtos.RatingDTO;
import edu.nyu.ratemyprofessor.objects.models.Rating;
import edu.nyu.ratemyprofessor.objects.models.SavedProfessor;
import edu.nyu.ratemyprofessor.objects.models.School;
import edu.nyu.ratemyprofessor.professor.model.dtos.ProfessorRatingDTO;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

@Entity
@Data
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

    @OneToMany(mappedBy = "professor")
    private List<Rating> ratingList;

    @OneToMany(mappedBy = "professor")
    private List<SavedProfessor> savedProfessorList;

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

    public static ProfessorRatingDTO toProfessorRatingDTO(Professor professor) {
        ProfessorRatingDTO dto = new ProfessorRatingDTO();
        dto.setId(professor.getId());
        dto.setFirstName(professor.getFirstName());
        dto.setLastName(professor.getLastName());
        dto.setDepartmentName(professor.getDepartmentName());
        dto.setSchoolId(professor.getSchoolId());
        dto.setSchoolName(professor.getSchoolName());

        List<RatingDTO> ratingDTOList = professor.getRatingList().stream()
                                                                .map(Rating::toRatingDTO)
                                                                .collect(Collectors.toList());
        
        dto.setRatingDTOList(ratingDTOList);
        return dto;
    }
}