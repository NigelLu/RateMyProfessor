package edu.nyu.ratemyprofessor.objects.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nyu.ratemyprofessor.objects.models.Rating;
import edu.nyu.ratemyprofessor.objects.repos.RatingRepository;
import edu.nyu.ratemyprofessor.objects.services.interfaces.RatingService;

@Service
public class RatingServiceImpl implements RatingService {
    
    private final RatingRepository ratingRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public Rating addRating(Rating rating){
        System.out.println(ratingRepository.count());
        return rating;
    }
}
