package app.core.controllers;

import app.core.auth.UserCredentials;
import app.core.entities.School;
import app.core.entities.Student;
import app.core.exception.SystemException;
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
    private SchoolDirectorService schoolDirectorService ;

    @PostMapping(headers = { HttpHeaders.AUTHORIZATION })
    @ResponseStatus(HttpStatus.CREATED)
    public void addStudent(@RequestBody Student student, HttpServletRequest req) throws SystemException {
        UserCredentials user = (UserCredentials) req.getAttribute("user");
        student.setId(user.getId());
        schoolDirectorService.addStudent(student);
    }

}
