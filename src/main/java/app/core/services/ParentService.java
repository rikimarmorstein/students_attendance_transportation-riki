package app.core.services;

import app.core.auth.UserCredentials;
import app.core.entities.Student;
import app.core.exception.SystemException;

import javax.security.auth.login.LoginException;
import javax.transaction.Transactional;

import app.core.auth.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ParentService extends ClientService{

@Autowired
    private JwtUtil jwtUtil;

    @Override
    public String login( UserCredentials userCredentials) throws SystemException, LoginException {
        if (studentRepo.existsByPhone(userCredentials.getPhone())) {
            Student student = studentRepo.findByPhone(userCredentials.getPhone());
            userCredentials.setId(student.getId());
            userCredentials.setName(student.getFirstName()+ " "+ student.getLastName());
            return this.jwtUtil.generateToken(userCredentials);
        }
        throw new LoginException("טלפון שגוי!");
    }

    public List<Student> getAllStudentsByPhone(String phone , int schoolId){
        return  this.studentRepo.findAllByPhone(phone);
    }

}
