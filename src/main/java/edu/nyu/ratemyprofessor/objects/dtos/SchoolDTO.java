package edu.nyu.ratemyprofessor.objects.dtos;

import edu.nyu.ratemyprofessor.professor.model.ProfessorDTO;

import java.util.List;

public class SchoolDTO {
    private Long id;
    private String name;
    private List<ProfessorDTO> professorDTOList;

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

    public List<ProfessorDTO> getProfessorDTOList() {
        return professorDTOList;
    }

    public void setProfessorDTOList(List<ProfessorDTO> professorDTOList) {
        this.professorDTOList = professorDTOList;
    }
}
