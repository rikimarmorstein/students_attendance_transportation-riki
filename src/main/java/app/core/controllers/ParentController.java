package app.core.controllers;

import app.core.auth.UserCredentials;
import app.core.entities.School;
import app.core.entities.Student;
import app.core.entities.Teacher;
import app.core.exception.SystemException;
import app.core.repositories.StudentRepo;
import app.core.repositories.TeacherRepo;
import app.core.services.ParentService;
import app.core.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/parent/")
public class ParentController {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private ParentService parentService;

    @GetMapping(path = "all-students-phone",headers = { HttpHeaders.AUTHORIZATION } )
    public List<Student> getAllStudentsByPhone (@RequestParam String phone, HttpServletRequest req) throws SystemException {
        UserCredentials user = (UserCredentials) req.getAttribute("user");
        Student student= studentRepo.findById(user.getId()).orElseThrow(() -> new SystemException("התלמיד לא קיים/ת במערכת"));
        School school= student.getSchool();
        return parentService.getAllStudentsByPhone(phone, school.getId());
    }

}
