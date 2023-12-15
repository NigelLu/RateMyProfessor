package edu.nyu.ratemyprofessor.objects.services.impls;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nyu.ratemyprofessor.objects.services.interfaces.SavedProfessorService;
import edu.nyu.ratemyprofessor.professor.model.Professor;
import edu.nyu.ratemyprofessor.professor.repo.ProfessorRepository;
import edu.nyu.ratemyprofessor.student.model.Student;
import edu.nyu.ratemyprofessor.student.repository.StudentRepository;
import edu.nyu.ratemyprofessor.objects.models.SavedProfessor;
import edu.nyu.ratemyprofessor.objects.repos.SavedProfessorRepository;

@Service
public class SavedProfessorServiceImpl implements SavedProfessorService {
    private final SavedProfessorRepository savedProfessorRepository;
    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;

    @Autowired
    public SavedProfessorServiceImpl(SavedProfessorRepository savedProfessorRepository,
            StudentRepository studentRepository, ProfessorRepository professorRepository) {
        this.savedProfessorRepository = savedProfessorRepository;
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
    }

    @Override
    public SavedProfessor addSavedProfessor(SavedProfessor savedProfessor) throws Exception {
        Long studentId = savedProfessor.getStudentId();
        Long professorId = savedProfessor.getProfessorId();

        // check if savedProfessor exists
        boolean exists = savedProfessorRepository.findByStudentIdAndProfessorId(studentId, professorId).isPresent();

        if (exists) {
            throw new Exception("Saved professor exists");
        }

        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new EntityNotFoundException("User not found with id: " + savedProfessor.getStudentId()));
        Professor professor = professorRepository.findById(professorId).orElseThrow(
                () -> new EntityNotFoundException("Professor not found with id: " + savedProfessor.getProfessorId()));

        savedProfessor.setStudent(student);
        savedProfessor.setProfessor(professor);
        savedProfessorRepository.save(savedProfessor);

        return savedProfessor;
    }

    @Override
    public void deleteSavedProfessor(Long studentId, Long professorId) throws EntityNotFoundException {
        SavedProfessor savedProfessor = savedProfessorRepository.findByStudentIdAndProfessorId(studentId, professorId)
                .orElseThrow(() -> new EntityNotFoundException("Failed to find the saved professor record"));
        savedProfessorRepository.deleteById(savedProfessor.getId());
    }
}
