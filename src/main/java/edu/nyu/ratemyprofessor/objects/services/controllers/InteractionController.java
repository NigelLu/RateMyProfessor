package edu.nyu.ratemyprofessor.objects.services.controllers;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import edu.nyu.ratemyprofessor.objects.models.Rating;
import edu.nyu.ratemyprofessor.objects.models.SavedProfessor;
import edu.nyu.ratemyprofessor.objects.services.interfaces.RatingService;
import edu.nyu.ratemyprofessor.objects.services.interfaces.SavedProfessorService;

@CrossOrigin
@RequestMapping(path = "")
@RestController
public class InteractionController {
    private RatingService ratingService;
    private SavedProfessorService savedProfessorService;


    @Autowired
    public InteractionController(RatingService ratingService, SavedProfessorService savedProfessorService) {
        this.ratingService = ratingService;
        this.savedProfessorService = savedProfessorService;
    }

    @PostMapping(path = "rate")
    public ResponseEntity<?> rateOnProfessor(@RequestBody Rating rating) {
        try {
            ratingService.addRating(rating);
            return ResponseEntity.ok(Rating.toRatingDTO(rating));
        }
        catch (EntityNotFoundException e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    } 

    @PostMapping(path = "save")
    public ResponseEntity<?> saveProfessor(@RequestBody SavedProfessor savedProfessor) {
        try {
            savedProfessorService.addSavedProfessor(savedProfessor);
            return ResponseEntity.ok(SavedProfessor.toSavedProfessorDTO(savedProfessor));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(path = "save")
    public ResponseEntity<?> deleteSavedProfessor(@RequestBody ObjectNode objectNode) {
        Long id = objectNode.get("id").asLong();

        try {
            savedProfessorService.deleteSavedProfessor(id);
            return ResponseEntity.ok(true);
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
