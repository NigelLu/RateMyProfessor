package edu.nyu.ratemyprofessor.objects.models;

import javax.persistence.*;

import edu.nyu.ratemyprofessor.objects.dtos.SavedProfessorDTO;
import edu.nyu.ratemyprofessor.professor.model.Professor;
import edu.nyu.ratemyprofessor.user.model.Student;
import lombok.Data;

@Data
@Entity
public class SavedProfessor{
    
    @Id
    @SequenceGenerator(name = "savedProfessorSequence", sequenceName = "savedProfessorSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "savedProfessorSequence")
    private Long id;

    private Long studentId;

    private Long professorId;

    @ManyToOne
    @JoinColumn(name = "professor")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "student")
    private Student student;

    public static SavedProfessorDTO toSavedProfessorDTO(SavedProfessor savedProfessor) {
        SavedProfessorDTO dto = new SavedProfessorDTO();
        dto.setId(savedProfessor.getId());
        dto.setProfessorId(savedProfessor.getProfessorId());
        dto.setStudentId(savedProfessor.getStudentId());

        return dto;
    }

}
