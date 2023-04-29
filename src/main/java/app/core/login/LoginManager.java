package app.core.login;

import app.core.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginManager {
    @Autowired
   private TeacherService teacherService;

    public String login(){
        return "sss";
    }


    public enum ClientType{
        ADMIN, TEACHER;
    }

}
