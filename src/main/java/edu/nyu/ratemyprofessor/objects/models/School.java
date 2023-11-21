package edu.nyu.ratemyprofessor.objects.models;

import edu.nyu.ratemyprofessor.objects.dtos.SchoolDTO;
import edu.nyu.ratemyprofessor.professor.model.Professor;
import edu.nyu.ratemyprofessor.user.model.Student;
import lombok.Data;

import javax.persistence.*;

import java.util.List;

@Entity
@Data
public class School {
    @Id
    @SequenceGenerator(name = "schoolSequence", sequenceName = "schoolSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "schoolSequence")
    private Long id;

    private String name;
    

    @OneToMany(mappedBy = "school")
    private List<Professor> professorList;

    @OneToMany(mappedBy = "school")
    private List<Student> studentList;

    public School() {
    }

    public School(String name) {
        this.name = name;
    }

    public School(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static SchoolDTO toSchoolDTO(School school) {
        SchoolDTO dto = new SchoolDTO();
        dto.setId(school.getId());
        dto.setName(school.getName());
        return dto;
    }
}
