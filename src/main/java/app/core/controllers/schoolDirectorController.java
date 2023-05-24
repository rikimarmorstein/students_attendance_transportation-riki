package app.core.controllers;

import app.core.auth.UserCredentials;
import app.core.entities.School;
import app.core.entities.Student;
import app.core.entities.Teacher;
import app.core.exception.SystemException;
import app.core.repositories.SchoolRepo;
import app.core.services.AdminService;
import app.core.services.SchoolDirectorService;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/schoolDirector/")
public class schoolDirectorController {
    @Autowired
    private SchoolRepo schoolRepo;
    @Autowired
    private SchoolDirectorService schoolDirectorService;

    @PutMapping(headers = {HttpHeaders.AUTHORIZATION}, path = "school")
    public void updateSchool(@RequestBody School school, HttpServletRequest req) throws SystemException {
        UserCredentials user = (UserCredentials) req.getAttribute("user");
        School schoolFromDb = schoolRepo.findById(user.getId()).orElseThrow(() -> new SystemException("בית הספר לא קיים במערכת"));
        schoolDirectorService.updateSchool(school, schoolFromDb.getId());
    }

    @PostMapping(headers = {HttpHeaders.AUTHORIZATION}, path = "teacher")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTeacher(@RequestBody Teacher teacher, HttpServletRequest req) throws SystemException {
        UserCredentials user = (UserCredentials) req.getAttribute("user");
        School school = schoolRepo.findById(user.getId()).orElseThrow(() -> new SystemException("בית הספר לא קיים במערכת"));
        teacher.setSchool(school);
        schoolDirectorService.addTeacher(teacher);
    }

    @PutMapping(headers = {HttpHeaders.AUTHORIZATION}, path = "teacher")
    public void updateTeacher(@RequestBody Teacher teacher, HttpServletRequest req) throws SystemException {
        UserCredentials user = (UserCredentials) req.getAttribute("user");
        School school = schoolRepo.findById(user.getId()).orElseThrow(() -> new SystemException("בית הספר לא קיים במערכת"));
        schoolDirectorService.updateTeacher(teacher, school.getId());
    }

    @DeleteMapping(headers = {HttpHeaders.AUTHORIZATION}, path = "teacher/{teacherId}")
    public void deleteTeacher(@PathVariable int teacherId, HttpServletRequest req) throws SystemException {
        UserCredentials user = (UserCredentials) req.getAttribute("user");
        School school = schoolRepo.findById(user.getId()).orElseThrow(() -> new SystemException("בית הספר לא קיים במערכת"));
        schoolDirectorService.deleteTeacher(teacherId, school.getId());
    }

    @GetMapping(headers = {HttpHeaders.AUTHORIZATION}, path = "all-teachers")
    public List<Teacher> getAllTeachers(HttpServletRequest req) throws SystemException {
        UserCredentials user = (UserCredentials) req.getAttribute("user");
        School school = schoolRepo.findById(user.getId()).orElseThrow(() -> new SystemException("בית הספר לא קיים במערכת"));
        return schoolDirectorService.getAllTeachers(school.getId());
    }

    @GetMapping(headers = {HttpHeaders.AUTHORIZATION}, path = "one-teacher/{teacherId}")
    public Teacher getOneTeacher(@PathVariable int teacherId, HttpServletRequest req) throws SystemException {
        UserCredentials user = (UserCredentials) req.getAttribute("user");
        School school = schoolRepo.findById(user.getId()).orElseThrow(() -> new SystemException("בית הספר לא קיים במערכת"));
        return schoolDirectorService.getOneTeacher(teacherId, school.getId());
    }

    @PostMapping(headers = {HttpHeaders.AUTHORIZATION}, path = "student")
    @ResponseStatus(HttpStatus.CREATED)
    public void addStudent(@RequestBody Student student, HttpServletRequest req) throws SystemException {
        UserCredentials user = (UserCredentials) req.getAttribute("user");
        School school = schoolRepo.findById(user.getId()).orElseThrow(() -> new SystemException("בית הספר לא קיים במערכת"));
        student.setSchool(school);
        schoolDirectorService.addStudent(student);
    }

    @PutMapping(headers = {HttpHeaders.AUTHORIZATION}, path = "student")
    public void updateStudent(@RequestBody Student student, HttpServletRequest req) throws SystemException {
        UserCredentials user = (UserCredentials) req.getAttribute("user");
        School school = schoolRepo.findById(user.getId()).orElseThrow(() -> new SystemException("בית הספר לא קיים במערכת"));
        schoolDirectorService.updateStudent(student, school.getId());
    }

    @DeleteMapping(headers = {HttpHeaders.AUTHORIZATION}, path = "student/{studentId}")
    public void deleteStudent(@PathVariable int studentId, HttpServletRequest req) throws SystemException {
        UserCredentials user = (UserCredentials) req.getAttribute("user");
        School school = schoolRepo.findById(user.getId()).orElseThrow(() -> new SystemException("בית הספר לא קיים במערכת"));
        schoolDirectorService.deleteStudent(studentId, school.getId());
    }

    @GetMapping(headers = {HttpHeaders.AUTHORIZATION}, path = "all-students")
    public List<Student> getAllStudents(HttpServletRequest req) throws SystemException {
        UserCredentials user = (UserCredentials) req.getAttribute("user");
        School school = schoolRepo.findById(user.getId()).orElseThrow(() -> new SystemException("בית הספר לא קיים במערכת"));
        return schoolDirectorService.getAllStudents(school.getId());
    }

    @GetMapping(headers = {HttpHeaders.AUTHORIZATION}, path = "one-student/{studentId}")
    public Student getOneStudent(@PathVariable int studentId, HttpServletRequest req) throws SystemException {
        UserCredentials user = (UserCredentials) req.getAttribute("user");
        School school = schoolRepo.findById(user.getId()).orElseThrow(() -> new SystemException("בית הספר לא קיים במערכת"));
        return schoolDirectorService.getOneStudent(studentId, school.getId());
    }

    @GetMapping(headers = {HttpHeaders.AUTHORIZATION}, path = "all-student-class")
    public List<Student> getAllStudentsByClass(int numClass, HttpServletRequest req) throws SystemException {
        UserCredentials user = (UserCredentials) req.getAttribute("user");
        School school = schoolRepo.findById(user.getId()).orElseThrow(() -> new SystemException("בית הספר לא קיים במערכת"));
    return schoolDirectorService.getAllStudentsByClass(numClass,school.getId());
    }
    @PutMapping(headers = {HttpHeaders.AUTHORIZATION}, path = "set-student-to-not-travel")
    public void setStudentToNotTravel(int studentId, HttpServletRequest req) throws SystemException{
        UserCredentials user = (UserCredentials) req.getAttribute("user");
        School school = schoolRepo.findById(user.getId()).orElseThrow(() -> new SystemException("בית הספר לא קיים במערכת"));
    schoolDirectorService.setStudentToNotTravel(studentId, school.getId());
    }
    @PutMapping(headers = {HttpHeaders.AUTHORIZATION}, path = "set-student-to-travel")
    public void setStudentToTravel(int studentId, HttpServletRequest req) throws SystemException {
        UserCredentials user = (UserCredentials) req.getAttribute("user");
        School school = schoolRepo.findById(user.getId()).orElseThrow(() -> new SystemException("בית הספר לא קיים במערכת"));
        schoolDirectorService.setStudentToTravel(studentId, school.getId());
    }

}
