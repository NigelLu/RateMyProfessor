package edu.nyu.ratemyprofessor.professor.controller;

import edu.nyu.ratemyprofessor.professor.model.Professor;
import edu.nyu.ratemyprofessor.professor.model.dtos.ProfessorRatingDTO;

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

    // professor/{professorId} 
    // for professor detail page show
    @GetMapping(path = "/{professorId}")
    public ResponseEntity<Optional<ProfessorRatingDTO>> getProfessor(@PathVariable("professorId") Long professorId) {
        Optional<Professor> professor = professorViewService.getProfessorDetailsById(professorId);

        
        if (professor.equals(null)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(professor.map(Professor::toProfessorRatingDTO));
    }

    // professor/list/{schoolId} 
    // for professor list showing in search listing and card listing
    @GetMapping(path = "/list/{schoolId}")
    public ResponseEntity<List<?>> getProfessorListBySchool(
            @PathVariable("schoolId") Long id,
            @RequestParam(required = true) Boolean includeDetails,
            @RequestParam(required = false) String name) {
        List<Professor> professors;
        List<?> dtoList;

        // For search listing view
        if (!includeDetails) {
            if (name != null && !name.trim().isEmpty()) {
                professors = professorViewService.getProfessorListBySchoolIdAndName(id, name, name);
            } else {
                professors = professorViewService.getProfessorListBySchoolId(id);
            }
            dtoList = professors.stream()
                    .map(Professor::toProfessorDTO)
                    .collect(Collectors.toList());
        }

        // For card listing view
        else {
            if (name != null && !name.trim().isEmpty()) {
                professors = professorViewService.getProfessorListDetailsBySchoolIdAndName(id, name, name);
            } else {
                professors = professorViewService.getProfessorListDetailsBySchoolId(id);
            }
            dtoList = professors.stream()
                    .map(Professor::toProfessorRatingDTO)
                    .collect(Collectors.toList());
        }

        if (professors.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(dtoList);
    }

    // professor/add 
    // for adding new professor
    @PostMapping(path = "add")
    // return type could be a new added professor or exception message
    public ResponseEntity<?> addProfessor(@RequestBody Professor newProfessor) {
        try {
            professorViewService.addNewProfessor(newProfessor);
            return ResponseEntity.ok(Professor.toProfessorDTO(newProfessor));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
