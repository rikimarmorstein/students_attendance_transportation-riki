package app.core.controllers;

import app.core.auth.UserCredentials;
import app.core.entities.School;
import app.core.entities.Student;
import app.core.entities.Teacher;
import app.core.exception.SystemException;
import app.core.repositories.SchoolRepo;
import app.core.services.AdminService;
import app.core.services.SchoolDirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/schoolDirector/")
public class schoolDirectorController {
    @Autowired
    private SchoolRepo schoolRepo;
    @Autowired
    private SchoolDirectorService schoolDirectorService ;

    @PutMapping(headers = { HttpHeaders.AUTHORIZATION }, path = "school")
    public void updateSchool(@RequestBody School school) throws SystemException {
        schoolDirectorService.updateSchool(school);
    }

    @PostMapping(headers = { HttpHeaders.AUTHORIZATION }, path = "student")
    @ResponseStatus(HttpStatus.CREATED)
    public void addStudent(@RequestBody Student student, HttpServletRequest req) throws SystemException {
        UserCredentials user = (UserCredentials) req.getAttribute("user");
        School school = schoolRepo.findById(user.getId()).orElseThrow(() -> new SystemException("בית הספר לא קיים במערכת"));
        student.setSchool(school);
        schoolDirectorService.addStudent(student);
    }
    @PostMapping(headers = { HttpHeaders.AUTHORIZATION }, path = "teacher")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTeacher(@RequestBody Teacher teacher, HttpServletRequest req) throws SystemException {
        UserCredentials user = (UserCredentials) req.getAttribute("user");
        School school = schoolRepo.findById(user.getId()).orElseThrow(() -> new SystemException("בית הספר לא קיים במערכת"));
        teacher.setSchool(school);
        schoolDirectorService.addTeacher(teacher);
    }

}
