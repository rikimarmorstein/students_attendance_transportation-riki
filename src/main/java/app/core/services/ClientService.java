package app.core.services;

import app.core.auth.UserCredentials;
import app.core.exception.SystemException;
import app.core.repositories.SchoolRepo;
import app.core.repositories.StudentRepo;
import app.core.repositories.TeacherRepo;
import app.core.repositories.TransportationRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.security.auth.login.LoginException;

public abstract class ClientService {
    @Autowired
    protected StudentRepo studentRepo;
    @Autowired
    protected TeacherRepo teacherRepo;
    @Autowired
    protected SchoolRepo schoolRepo;

    @Autowired
    protected TransportationRepo transportationRepo;

    public abstract String login(UserCredentials userCredentials) throws SystemException, LoginException;
}
