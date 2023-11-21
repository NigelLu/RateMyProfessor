package edu.nyu.ratemyprofessor.professor.controller;

import edu.nyu.ratemyprofessor.professor.model.Professor;

import java.util.List;

public interface ProfessorViewService {

    // for selection listing view
    List<Professor> getProfessorListBySchoolId(Long schoolId);

    List<Professor> getProfessorListBySchoolIdAndName(Long schoolId,
                                                      String firstName,
                                                      String lastName);

    // for card listing view, also returns rating information
    List<Professor> getProfessorListDetailsBySchoolId(Long schoolId);

    List<Professor> getProfessorListDetailsBySchoolIdAndName(Long schoolId,
                                                             String firstName,
                                                             String lastName);

    // add new Professor
    Professor addNewProfessor(Professor professor) throws Exception;

    // get Professor details
    Professor getProfessorDetailsById(Long professorId);

}
