package edu.nyu.ratemyprofessor.objects.services.interfaces;

import edu.nyu.ratemyprofessor.objects.dtos.SchoolDTO;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface SchoolService {

    List<SchoolDTO> getSchools();

    String getSchool(Long id) throws JsonProcessingException;
}
