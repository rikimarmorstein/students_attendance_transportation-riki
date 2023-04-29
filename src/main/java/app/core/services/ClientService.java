package app.core.services;

import app.core.repositories.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClientService {
    @Autowired
    protected StudentRepo studentRepo;
    @Autowired
    protected TeacherService teacherService;

    public abstract String login();
}
