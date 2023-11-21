package edu.nyu.ratemyprofessor.objects.services.impls;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nyu.ratemyprofessor.objects.services.interfaces.SavedProfessorService;
import edu.nyu.ratemyprofessor.professor.model.Professor;
import edu.nyu.ratemyprofessor.professor.repo.ProfessorRepository;
import edu.nyu.ratemyprofessor.user.model.Student;
import edu.nyu.ratemyprofessor.user.repository.StudentRepository;
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
    public SavedProfessor addSavedProfessor(SavedProfessor savedProfessor) throws Exception{
        Long studentId = savedProfessor.getStudentId();
        Long professorId = savedProfessor.getProfessorId();

        // check if savedProfessor exists
        boolean exists = savedProfessorRepository.findByStudentIdAndProfessorId(studentId, professorId).isPresent();

        if (exists) {
            throw new Exception("Saved professor exists");
        }

        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Optional<Professor> professorOptional = professorRepository.findById(professorId);
        
        Student student = studentOptional.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + savedProfessor.getStudentId()));
        Professor professor = professorOptional.orElseThrow(() -> new EntityNotFoundException("Professor not found with id: " + savedProfessor.getProfessorId()));

        savedProfessor.setStudent(student);
        savedProfessor.setProfessor(professor);
        savedProfessorRepository.save(savedProfessor);

        return savedProfessor;
    }

    @Override
    public void deleteSavedProfessor(Long id) {
        Optional<SavedProfessor> savedProfessorOptional = savedProfessorRepository.findById(id);
        savedProfessorOptional.orElseThrow(() -> new EntityNotFoundException("Saved professor with id:" + id + " not exists"));
        savedProfessorRepository.deleteById(id);
    }
}
