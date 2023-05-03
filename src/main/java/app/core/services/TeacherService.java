package app.core.services;

import app.core.auth.UserCredentials;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeacherService extends  ClientService{


    @Override
    public String login(UserCredentials userCredentials) {
        return null;
    }
}
