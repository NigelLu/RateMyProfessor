package edu.nyu.ratemyprofessor.objects.models;

import javax.persistence.*;

import edu.nyu.ratemyprofessor.objects.dtos.SavingDTO;
import edu.nyu.ratemyprofessor.professor.model.Professor;

@Table
@Entity
public class Saving {
    
    @Id
    @SequenceGenerator(name = "savingSequence", sequenceName = "savingSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "savingSequence")
    private Long id;

    private Long userId;

    private Long professorId;

    @ManyToOne
    @JoinColumn(name = "professor")
    private Professor professor;

    public Saving() {
    }

    // press space to select different attributes
    public Saving(Long userId, Long professorId) {
        this.userId = userId;
        this.professorId = professorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public static SavingDTO savingDTO(Saving saving) {
        SavingDTO dto = new SavingDTO();
        dto.setId(saving.getId());
        dto.setProfessorId(saving.getProfessorId());
        dto.setUserId(saving.getUserId());

        return dto;
    }
}
