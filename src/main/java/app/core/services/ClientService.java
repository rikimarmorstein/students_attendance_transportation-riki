package app.core.services;

import app.core.repositories.StudentRepo;
import app.core.repositories.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClientService {
    @Autowired
    protected StudentRepo studentRepo;
    @Autowired
    protected TeacherRepo teacherRepo;

    public abstract String login();
}
