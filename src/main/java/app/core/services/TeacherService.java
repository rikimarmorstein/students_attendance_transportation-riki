package app.core.services;

import app.core.auth.JwtUtil;
import app.core.auth.UserCredentials;
import app.core.entities.School;
import app.core.entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.LoginException;

@Service
@Transactional
public class TeacherService extends  ClientService{

    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public String login(UserCredentials userCredentials)  throws LoginException {
        if (teacherRepo.existsByPhoneAndPassword(userCredentials.getPhone(), userCredentials.getPassword())) {
            Teacher teacher = teacherRepo.findByPhoneAndPassword(userCredentials.getPhone(),
                    userCredentials.getPassword());
            userCredentials.setId(teacher.getId());
            userCredentials.setName(teacher.getFirstName()+ " "+ teacher.getLastName());
            return this.jwtUtil.generateToken(userCredentials);
        }
        throw new LoginException("טלפון וסיסמא שגויים!");
    }
}
