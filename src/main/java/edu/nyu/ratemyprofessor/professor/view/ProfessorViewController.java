package edu.nyu.ratemyprofessor.professor.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.nyu.ratemyprofessor.objects.models.School;
import edu.nyu.ratemyprofessor.professor.model.Professor;
import edu.nyu.ratemyprofessor.professor.model.ProfessorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RequestMapping(path = "professor")
@RestController
public class ProfessorViewController {
    private final ProfessorViewService professorViewService;

    @Autowired
    public ProfessorViewController(ProfessorViewService professorViewService) {
        this.professorViewService = professorViewService;
    }

    // professor/{professorId} for professor detail page show
    @GetMapping(path="/{professorId}")
    public ResponseEntity<Optional<ProfessorDTO>> getProfessor (@PathVariable("professorId") Long professorId) {
        Optional<Professor> professor = professorViewService.getProfessorDetailsById(professorId);
        if (professor.equals(null)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(professor.map(Professor::toProfessorDTO));
    }


    // professor/list/{schoolId} for professor list showing in search listing
    // and card listing
    @GetMapping(path="/list/{schoolId}")
    public ResponseEntity<List<ProfessorDTO>> getProfessorListBySchool(
            @PathVariable("schoolId") Long id,
            @RequestParam(required = true) Boolean includeDetails,
            @RequestParam(required = false) String name){
        List<Professor> professors;

        // For search listing view
        if (!includeDetails){
            if (name != null && !name.trim().isEmpty()){
                professors = professorViewService.getProfessorListBySchoolIdAndName(id, name, name);
            }
            else{
                professors = professorViewService.getProfessorListBySchoolId(id);
            }
        }

        // For card listing view
        else{
            if (name != null && !name.trim().isEmpty()){
                professors = professorViewService.getProfessorListDetailsBySchoolIdAndName(id, name, name);
            }
            else{
                professors = professorViewService.getProfessorListDetailsBySchoolId(id);
            }
        }

        if (professors.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(professors.stream()
                .map(Professor::toProfessorDTO)
                .collect(Collectors.toList())
        );
    }

    // professor/add for adding new professor
    @PostMapping(path = "add")
    // return type could be a new added professor or exception message
    public ResponseEntity<?> addProfessor(@RequestBody ObjectNode objectNode){
        String firstName = objectNode.get("firstName").asText();
        String lastName = objectNode.get("lastName").asText();
        String schoolName = objectNode.get("schoolName").asText();
        String departmentName = objectNode.get("departmentName").asText();
        Long schoolId = objectNode.get("schoolId").asLong();

        Professor newProfessor = new Professor(firstName, lastName, schoolName, departmentName, schoolId);

        try {
            professorViewService.addNewProfessor(newProfessor);
            return ResponseEntity.ok(Professor.toProfessorDTO(newProfessor));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
