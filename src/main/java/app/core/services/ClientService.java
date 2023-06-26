package app.core.services;

import app.core.auth.UserCredentials;
import app.core.exception.SystemException;
import app.core.repositories.*;
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
    protected StationRepo stationRepo;

    @Autowired
    protected TransportationRepo transportationRepo;

    public abstract String login(UserCredentials userCredentials) throws SystemException, LoginException;
}
