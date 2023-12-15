package edu.nyu.ratemyprofessor.professor.model.dtos;

import java.util.List;

import edu.nyu.ratemyprofessor.objects.dtos.RatingDTO;
import edu.nyu.ratemyprofessor.professor.model.ProfessorDTO;

// This model will return all rating details of the professor.
public class ProfessorRatingDTO extends ProfessorDTO{

    private List<RatingDTO> ratingDTOList;

    public List<RatingDTO> getRatingDTOList() {
        return ratingDTOList;
    }

    public void setRatingDTOList(List<RatingDTO> ratingDTOList) {
        this.ratingDTOList = ratingDTOList;
    }
    
}
