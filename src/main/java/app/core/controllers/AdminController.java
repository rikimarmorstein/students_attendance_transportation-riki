package app.core.controllers;

import app.core.auth.UserCredentials;
import app.core.entities.School;
import app.core.exception.SystemException;
import app.core.login.LoginManager;
import app.core.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/")
public class AdminController {

@Autowired
    private AdminService adminService;

    @Autowired
    private LoginManager loginManager;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String login(@RequestBody UserCredentials userCredentials) throws SystemException, LoginException {
        return this.loginManager.login(userCredentials);
    }

    @PostMapping(headers = { HttpHeaders.AUTHORIZATION },path = "school")
    @ResponseStatus(HttpStatus.CREATED)
    public void addSchool(@RequestBody School school, HttpServletRequest req) throws SystemException {
        UserCredentials user = (UserCredentials) req.getAttribute("user");
        adminService.addSchool(school, user.getId());
    }

@PutMapping(headers = { HttpHeaders.AUTHORIZATION },path = "school")
    public void updateSchool(@RequestBody School school, HttpServletRequest req) throws SystemException {
    UserCredentials user = (UserCredentials) req.getAttribute("user");
    adminService.updateSchool(school, user.getId());
    }

@DeleteMapping(path = "school/{schoolId}", headers = { HttpHeaders.AUTHORIZATION })
    public void deleteSchool(@PathVariable int schoolId, HttpServletRequest req) throws SystemException {
    UserCredentials user = (UserCredentials) req.getAttribute("user");
    adminService.deleteSchool(schoolId, user.getId());
    }

    @GetMapping(path = "all-schools",headers = { HttpHeaders.AUTHORIZATION } )
    public List<School> getAllSchools(HttpServletRequest req) throws SystemException {
        UserCredentials user = (UserCredentials) req.getAttribute("user");
       return adminService.getAllSchools(user.getId());
    }

    @GetMapping(path = "one-school/{schoolId}",headers = { HttpHeaders.AUTHORIZATION } )
    public School getOneSchool(@PathVariable int schoolId, HttpServletRequest req) throws SystemException {
        UserCredentials user = (UserCredentials) req.getAttribute("user");
        return adminService.getOneSchool(schoolId, user.getId());
    }



}
