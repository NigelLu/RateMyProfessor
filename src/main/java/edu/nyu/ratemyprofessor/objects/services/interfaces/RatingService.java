package edu.nyu.ratemyprofessor.objects.services.interfaces;

import javax.persistence.EntityNotFoundException;

import edu.nyu.ratemyprofessor.objects.models.Rating;

public interface RatingService {
    Rating addRating(Rating rating);

    Rating findRating(Long id) throws EntityNotFoundException;

    Rating editRating(Rating rating) throws EntityNotFoundException;
}
