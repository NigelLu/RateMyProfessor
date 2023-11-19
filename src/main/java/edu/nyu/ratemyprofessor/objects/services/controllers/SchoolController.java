package edu.nyu.ratemyprofessor.objects.services.controllers;

import edu.nyu.ratemyprofessor.objects.dtos.SchoolDTO;
import edu.nyu.ratemyprofessor.objects.services.interfaces.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RequestMapping(path = "school")
@RestController
public class SchoolController {
    private final SchoolService schoolService;



    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping
    public List<SchoolDTO> getSchools() {
        return schoolService.getSchools();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<SchoolDTO>> getSchool (@PathVariable("id") Long id) {
        Optional<SchoolDTO> school = schoolService.getSchool(id);
        if (school.equals(null)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(school);
    }
}
