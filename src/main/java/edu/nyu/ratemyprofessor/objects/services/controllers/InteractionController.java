package edu.nyu.ratemyprofessor.objects.services.controllers;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping(path = "edit/rating/{ratingId}")
    public ResponseEntity<?> getRating(@PathVariable("ratingId") Long ratingId) {
        try {
            Rating rating = ratingService.findRating(ratingId);
            return ResponseEntity.ok(Rating.toRatingDTO(rating));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping(path = "rate")
    public ResponseEntity<?> rateOnProfessor(@RequestBody Rating rating) {
        try {
            ratingService.addRating(rating);
            return ResponseEntity.ok(Rating.toRatingDTO(rating));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping(path = "edit/rating")
    public ResponseEntity<?> editRating(@RequestBody Rating rating) {
        try {
            Rating thisRating = ratingService.editRating(rating);
            return ResponseEntity.ok(Rating.toRatingDTO(thisRating));
        } catch (IllegalStateException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping(path = "save")
    public ResponseEntity<?> saveProfessor(@RequestBody SavedProfessor savedProfessor) {
        try {
            savedProfessorService.addSavedProfessor(savedProfessor);
            return ResponseEntity.ok(SavedProfessor.toSavedProfessorDTO(savedProfessor));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping(path = "save")
    public ResponseEntity<?> deleteSavedProfessor(@RequestBody ObjectNode objectNode) {
        try {
            Long id = objectNode.get("id").asLong();
            savedProfessorService.deleteSavedProfessor(id);
            return ResponseEntity.ok(true);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
