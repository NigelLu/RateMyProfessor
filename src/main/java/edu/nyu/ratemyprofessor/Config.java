package edu.nyu.ratemyprofessor;

import edu.nyu.ratemyprofessor.objects.models.Rating;
import edu.nyu.ratemyprofessor.objects.models.School;
import edu.nyu.ratemyprofessor.objects.repos.SchoolRepository;
import edu.nyu.ratemyprofessor.objects.services.interfaces.RatingService;
import edu.nyu.ratemyprofessor.professor.model.Professor;
import edu.nyu.ratemyprofessor.professor.repo.ProfessorRepository;
import edu.nyu.ratemyprofessor.student.controller.StudentService;
import edu.nyu.ratemyprofessor.student.model.Student;
import edu.nyu.ratemyprofessor.utils.Grade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityNotFoundException;

import org.springframework.context.ApplicationContext;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.Iterator;
import java.io.InputStream;
import org.springframework.core.io.Resource;

import java.time.LocalDateTime;

@Configuration
public class Config {

    private final SchoolRepository schoolRepository;
    private final ProfessorRepository professorRepository;
    private final StudentService studentService;
    private final RatingService ratingService;
    private ApplicationContext applicationContext;

    @Autowired
    public Config(SchoolRepository schoolRepository, ProfessorRepository professorRepository,
            ApplicationContext applicationContext,
            StudentService studentService, RatingService ratingService) {
        this.schoolRepository = schoolRepository;
        this.professorRepository = professorRepository;
        this.studentService = studentService;
        this.applicationContext = applicationContext;
        this.ratingService = ratingService;
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            if (schoolRepository.findAll().isEmpty()) {

                // Read school.xlsx to init the School table
                try {
                    Resource schoolResource = applicationContext.getResource("classpath:preloadingData/School.xlsx");
                    InputStream schoolInputStream = schoolResource.getInputStream();

                    Workbook schoolWorkbook = new XSSFWorkbook(schoolInputStream);
                    Sheet schoolSheet = schoolWorkbook.getSheetAt(0);

                    Iterator<Row> schoolRowIterator = schoolSheet.iterator();
                    Row schoolRow = schoolRowIterator.next();
                    while (schoolRowIterator.hasNext()) {
                        schoolRow = schoolRowIterator.next();
                        Iterator<Cell> schoolCellIterator = schoolRow.cellIterator();

                        while (schoolCellIterator.hasNext()) {
                            Cell schoolCell = schoolCellIterator.next();
                            switch (schoolCell.getCellType()) {
                                case STRING:
                                    School newSchool = new School(schoolCell.getStringCellValue());
                                    schoolRepository.save(newSchool);
                                    break;
                                default:
                            }
                        }
                    }
                    schoolWorkbook.close();
                    schoolInputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Read Professor.xlsx to init the Professor table
                try {
                    Resource professorResource = applicationContext
                            .getResource("classpath:preloadingData/Professor.xlsx");
                    InputStream professorInputStream = professorResource.getInputStream();

                    Workbook professorWorkbook = new XSSFWorkbook(professorInputStream);
                    Sheet professorSheet = professorWorkbook.getSheetAt(0);

                    Iterator<Row> professorRowIterator = professorSheet.iterator();
                    Row professorRow = professorRowIterator.next();
                    while (professorRowIterator.hasNext()) {
                        professorRow = professorRowIterator.next();
                        Iterator<Cell> professorCellIterator = professorRow.cellIterator();
                        int count = 0;
                        StringBuffer firstNameBuffer = new StringBuffer();
                        StringBuffer lastNameBuffer = new StringBuffer();
                        StringBuffer schoolNameBuffer = new StringBuffer();
                        StringBuffer departmentNameBuffer = new StringBuffer();

                        while (professorCellIterator.hasNext()) {
                            Cell professorCell = professorCellIterator.next();
                            if (count == 0) {
                                firstNameBuffer.append(professorCell.getStringCellValue());
                            } else if (count == 1) {
                                lastNameBuffer.append(professorCell.getStringCellValue());
                            } else if (count == 2) {
                                schoolNameBuffer.append(professorCell.getStringCellValue());
                            } else {
                                departmentNameBuffer.append(professorCell.getStringCellValue());
                            }
                            count += 1;
                        }

                        Professor newProfessor = new Professor(
                                firstNameBuffer.toString(),
                                lastNameBuffer.toString(),
                                schoolNameBuffer.toString(),
                                departmentNameBuffer.toString());

                        School thisSchool = schoolRepository.findByName(schoolNameBuffer.toString())
                                .orElseThrow(() -> new EntityNotFoundException(
                                        "School '" + schoolNameBuffer.toString() + "' not found."
                                                + " Professor name: " + firstNameBuffer.toString() + " "
                                                + lastNameBuffer.toString()));
                        newProfessor.setSchool(thisSchool);
                        newProfessor.setSchoolId(thisSchool.getId());
                        professorRepository.save(newProfessor);
                    }
                    professorWorkbook.close();
                    professorInputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                School NYU = schoolRepository.findByName("New York University")
                        .orElseThrow(() -> new EntityNotFoundException());

                Student student = new Student(
                        "pw1298@nyu.edu",
                        "Weng",
                        "123456",
                        "Jay",
                        NYU.getId());
                student.setSchool(NYU);
                student.setExpectedYearOfGraduation(2025);

                studentService.addNewStudent(student);
                Professor professor = professorRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException());
                Rating rating = new Rating((float) 5.0, (float) 2.5, true, true, true, Grade.A, "Good professor", 1L,
                        student.getId(),
                        LocalDateTime.now(), professor, student);
                ratingService.addRating(rating);
            }
        };
    }

}
