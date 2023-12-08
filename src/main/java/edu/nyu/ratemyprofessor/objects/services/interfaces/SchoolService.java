package edu.nyu.ratemyprofessor.objects.services.interfaces;

import edu.nyu.ratemyprofessor.objects.dtos.SchoolDTO;
import edu.nyu.ratemyprofessor.objects.models.School;

import java.util.List;
import java.util.Map;

public interface SchoolService {

    List<SchoolDTO> getSchools();

    School getSchoolById(Long id);

    Map<String, Object> getSchool(Long id);
}
