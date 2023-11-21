package edu.nyu.ratemyprofessor.objects.services.interfaces;

import javax.persistence.EntityNotFoundException;

import edu.nyu.ratemyprofessor.objects.models.SavedProfessor;

public interface SavedProfessorService {
    SavedProfessor addSavedProfessor(SavedProfessor savedProfessors) throws Exception;

    void deleteSavedProfessor(Long id) throws EntityNotFoundException;
}
