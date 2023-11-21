package edu.nyu.ratemyprofessor.objects.services.interfaces;

import edu.nyu.ratemyprofessor.objects.dtos.SchoolDTO;

import java.util.List;
import java.util.Map;

public interface SchoolService {

    List<SchoolDTO> getSchools();

    Map<String, Object> getSchool(Long id);
}
