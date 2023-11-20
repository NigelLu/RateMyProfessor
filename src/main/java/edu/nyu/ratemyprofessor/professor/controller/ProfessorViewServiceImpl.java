package edu.nyu.ratemyprofessor.professor.controller;

import edu.nyu.ratemyprofessor.objects.models.School;
import edu.nyu.ratemyprofessor.objects.repos.SchoolRepository;
import edu.nyu.ratemyprofessor.professor.model.Professor;
import edu.nyu.ratemyprofessor.professor.repo.ProfessorRepository;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorViewServiceImpl implements ProfessorViewService {
    private final ProfessorRepository professorRepository;
    private final SchoolRepository schoolRepository;

    @Autowired
    public ProfessorViewServiceImpl(ProfessorRepository professorRepository,
            SchoolRepository schoolRepository) {
        this.professorRepository = professorRepository;
        this.schoolRepository = schoolRepository;
    }

    @Override
    public List<Professor> getProfessorListBySchoolId(Long schoolId) {
        return professorRepository.findBySchoolId(schoolId);
    }

    @Override
    public List<Professor> getProfessorListBySchoolIdAndName(Long schoolId, String firstName, String lastName) {
        return professorRepository.findBySchoolIdAndFirstNameContainingOrLastNameContaining(schoolId, firstName,
                lastName);
    }

    @Override
    public List<Professor> getProfessorListDetailsBySchoolId(Long schoolId) {
        // need to add ratings return
        return professorRepository.findBySchoolId(schoolId);
    }

    @Override
    public List<Professor> getProfessorListDetailsBySchoolIdAndName(Long schoolId, String firstName, String lastName) {
        // need to add ratings return
        return professorRepository.findBySchoolIdAndFirstNameContainingOrLastNameContaining(schoolId, firstName,
                lastName);
    }

    @Override
    public Professor addNewProfessor(Professor professor) throws Exception {
        // check if the professor exists
        boolean exists = professorRepository.findByFirstNameAndLastNameAndSchoolNameAndDepartmentName(
                professor.getFirstName(),
                professor.getLastName(),
                professor.getSchoolName(),
                professor.getDepartmentName()).isPresent();

        if (exists) {
            throw new Exception("Professor already exists in the department of this school.");
        }

        Optional<School> school = schoolRepository.findById(professor.getSchoolId());
        School foundSchool = school.orElseThrow(() -> new EntityNotFoundException("School not found with id: " +
                professor.getSchoolId()));

        // set many to one mapping
        professor.setSchool(foundSchool);
        professorRepository.save(professor);

        return professor;
    }

    @Override
    public Optional<Professor> getProfessorDetailsById(Long professorId) {
        Optional<Professor> optionalProfessor = professorRepository.findById(professorId);
        return optionalProfessor;
    }

}
