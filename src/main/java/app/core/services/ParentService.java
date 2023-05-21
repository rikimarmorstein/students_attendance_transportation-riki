package app.core.services;

import app.core.auth.UserCredentials;
import app.core.exception.SystemException;

import javax.security.auth.login.LoginException;

public class ParentService extends ClientService{
    @Override
    public String login(UserCredentials userCredentials) throws SystemException, LoginException {
        return null;
    }
}
