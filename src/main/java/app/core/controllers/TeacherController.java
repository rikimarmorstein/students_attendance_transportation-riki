package app.core.controllers;

import app.core.auth.UserCredentials;
import app.core.entities.School;
import app.core.entities.Student;
import app.core.entities.Teacher;
import app.core.exception.SystemException;
import app.core.repositories.TeacherRepo;
import app.core.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/teacher/")
public class TeacherController {

    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private TeacherService teacherService;

    @GetMapping(path = "all-students",headers = { HttpHeaders.AUTHORIZATION } )
    public List<Student> getAllStudents (HttpServletRequest req) throws SystemException {
        UserCredentials user = (UserCredentials) req.getAttribute("user");
        Teacher teacher = teacherRepo.findById(user.getId()).orElseThrow(() -> new SystemException("המורה לא קיים/ת במערכת"));
      School school=  teacher.getSchool();
    return this.teacherService.getAllStudents(school.getId());
    }

}
