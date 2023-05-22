package app.core.services;

import app.core.auth.UserCredentials;
import app.core.entities.Student;
import app.core.exception.SystemException;

import javax.security.auth.login.LoginException;
import app.core.auth.JwtUtil;

import java.util.List;

@Service
@Transactional

@Autowired
private JwtUtil jwtUtil;
public class ParentService extends ClientService{

    @Override
    public String login(UserCredentials userCredentials) throws SystemException, LoginException {
        if (studentRepo.existsByPhone(userCredentials.getPhone())) {
           Student student = studentRepo.findByPhone(userCredentials.getPhone());
            userCredentials.setId(student.getId());
            userCredentials.setName(student.getFirstName()+ " "+ student.getLastName());
            return this.jwtUtil.generateToken(userCredentials);
        }
        throw new LoginException("טלפון שגוי!");
    }

    public List<Student> getAllStudentsByPhone(String phone){
        return  this.studentRepo.findAllByPhone(phone);
    }

}
