package edu.nyu.ratemyprofessor.objects.services.impls;

import edu.nyu.ratemyprofessor.objects.dtos.SchoolDTO;
import edu.nyu.ratemyprofessor.objects.models.School;
import edu.nyu.ratemyprofessor.objects.repos.SchoolRepository;
import edu.nyu.ratemyprofessor.objects.services.interfaces.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolServiceImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public List<SchoolDTO> getSchools() {
        List<School> schools = schoolRepository.findAll();
        return schools.stream()
                .map(School::toSchoolDTO)
                .collect(Collectors.toList());
    }

    public Optional<SchoolDTO> getSchool(Long id) {
        Optional<School> school = schoolRepository.findById(id);
        return school.map(School::toSchoolDTO);
    }
}
