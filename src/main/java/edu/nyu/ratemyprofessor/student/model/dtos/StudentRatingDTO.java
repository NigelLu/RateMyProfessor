package edu.nyu.ratemyprofessor.student.model.dtos;

import java.util.List;

import edu.nyu.ratemyprofessor.objects.dtos.RatingDTO;

public class StudentRatingDTO extends StudentDTO{
    private List<RatingDTO> ratingDTOList;

    public List<RatingDTO> getRatingDTOList() {
        return ratingDTOList;
    }

    public void setRatingDTOList(List<RatingDTO> ratingDTOList) {
        this.ratingDTOList = ratingDTOList;
    }

}
