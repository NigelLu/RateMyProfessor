package edu.nyu.ratemyprofessor.objects.services.interfaces;

import edu.nyu.ratemyprofessor.objects.dtos.SchoolDTO;
import edu.nyu.ratemyprofessor.objects.models.School;
import edu.nyu.ratemyprofessor.professor.model.Professor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface SchoolService {

    List<SchoolDTO> getSchools();

    Optional<SchoolDTO> getSchool(Long id);
}
