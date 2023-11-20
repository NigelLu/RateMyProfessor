package edu.nyu.ratemyprofessor.objects.services.impls;

import edu.nyu.ratemyprofessor.objects.dtos.SchoolDTO;
import edu.nyu.ratemyprofessor.objects.models.School;
import edu.nyu.ratemyprofessor.objects.repos.SchoolRepository;
import edu.nyu.ratemyprofessor.objects.services.interfaces.SchoolService;
import edu.nyu.ratemyprofessor.professor.model.Professor;
import edu.nyu.ratemyprofessor.professor.model.ProfessorDTO;
import edu.nyu.ratemyprofessor.professor.repo.ProfessorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

@Service
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;
    private final ProfessorRepository professorRepository;

    @Autowired
    public SchoolServiceImpl(SchoolRepository schoolRepository, ProfessorRepository professorRepository) {
        this.schoolRepository = schoolRepository;
        this.professorRepository = professorRepository;
    }

    public List<SchoolDTO> getSchools() {
        List<School> schools = schoolRepository.findAll();
        return schools.stream()
                .map(School::toSchoolDTO)
                .collect(Collectors.toList());
    }



    public String getSchool(Long id) throws JsonProcessingException {
        Optional<School> school = schoolRepository.findById(id);
        SchoolDTO schoolEntity = School.toSchoolDTO(school.orElseThrow(() -> new EntityNotFoundException("School not found with id: " + id)));

        List<Professor> professorList = professorRepository.findBySchoolId(id);
        List<ProfessorDTO> professorDTOList = professorList.stream()
                                                            .map(Professor::toProfessorDTO)
                                                            .toList();

        ObjectMapper mapper = new ObjectMapper();

        ObjectNode objectNode = mapper.createObjectNode();
        String jsonSchool = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(schoolEntity);
        String jsonProfessor = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(professorDTOList);
        objectNode.put("school", jsonSchool);
        objectNode.put("professor", jsonProfessor);

        String jsonObjectNode = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectNode);
        
        return jsonObjectNode;
    }
}
