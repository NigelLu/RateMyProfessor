package edu.nyu.ratemyprofessor.user.model.dtos;

import java.util.List;

import edu.nyu.ratemyprofessor.objects.dtos.SavedProfessorDTO;
import edu.nyu.ratemyprofessor.user.model.StudentDTO;

public class StudentSavedProfessorDTO extends StudentDTO{
    private List<SavedProfessorDTO> savedProfessorDTOList;

    public List<SavedProfessorDTO> getSavedProfessorDTOList() {
        return savedProfessorDTOList;
    }

    public void setSavedProfessorDTOList(List<SavedProfessorDTO> savedProfessorDTOList) {
        this.savedProfessorDTOList = savedProfessorDTOList;
    }
}