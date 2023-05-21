package app.core.login;

import app.core.auth.UserCredentials;
import app.core.exception.SystemException;
import app.core.services.AdminService;
import app.core.services.SchoolDirectorService;
import app.core.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
public class LoginManager {

    @Autowired
    private AdminService adminService;

    @Autowired
    private SchoolDirectorService schoolDirectorService;

    @Autowired
    private TeacherService teacherService;

    public String login(UserCredentials userCredentials) throws SystemException, LoginException {
        switch (userCredentials.getClientType().name()) {

            case "ADMIN":

                String tokenAdmin = adminService.login(userCredentials);
                return tokenAdmin;

            case "SCHOOL_DIRECTOR":

                String tokenSchoolDirectorService = schoolDirectorService.login(userCredentials);
                return tokenSchoolDirectorService;

            case "TEACHER":

                String tokenTeacherService = teacherService.login(userCredentials);
                return tokenTeacherService;

            case "PARENT":

                String tokenParentService = teacherService.login(userCredentials);
                return tokenTeacherService;

            default:
                throw new SystemException("Invalid Client Type");
        }

}

}
