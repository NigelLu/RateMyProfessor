package edu.nyu.ratemyprofessor.objects.models;

import edu.nyu.ratemyprofessor.objects.dtos.SchoolDTO;
import edu.nyu.ratemyprofessor.professor.model.Professor;
import edu.nyu.ratemyprofessor.professor.model.ProfessorDTO;
import javax.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table
public class School {
    @Id
    @SequenceGenerator(name = "schoolSequence", sequenceName = "schoolSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "schoolSequence")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "school")
    private List<Professor> professorList;

    public School() {
    }

    public School(String name) {
        this.name = name;
    }

    public School(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Professor> getProfessorList() {
        return professorList;
    }

    public void setProfessorList(List<Professor> professorList) {
        this.professorList = professorList;
    }

    public static SchoolDTO toSchoolDTO(School school) {
        SchoolDTO dto = new SchoolDTO();
        dto.setId(school.getId());
        dto.setName(school.getName());
        List<ProfessorDTO> professorDTOList = school.getProfessorList().stream()
                .map(Professor::toProfessorDTO)
                .collect(Collectors.toList());
        dto.setProfessorDTOList(professorDTOList);
        return dto;
    }
}
