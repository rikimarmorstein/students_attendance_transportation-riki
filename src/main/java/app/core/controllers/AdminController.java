package app.core.controllers;

import app.core.auth.UserCredentials;
import app.core.entities.School;
import app.core.exception.SystemException;
import app.core.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/")
public class AdminController {

@Autowired
    private AdminService adminService;
    //controller

    @PostMapping(headers = { HttpHeaders.AUTHORIZATION })
    @ResponseStatus(HttpStatus.CREATED)
    public void addSchool(@RequestBody School school, HttpServletRequest req) throws SystemException {
        UserCredentials user = (UserCredentials) req.getAttribute("user");
        adminService.addSchool(school, user.getId());
    }

@PutMapping(headers = { HttpHeaders.AUTHORIZATION })
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
