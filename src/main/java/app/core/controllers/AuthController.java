package app.core.controllers;

import javax.security.auth.login.LoginException;

import app.core.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.core.auth.UserCredentials;
import app.core.login.LoginManager;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth/")
public class AuthController {

	@Autowired
	private LoginManager loginManager;

	@PostMapping("login")
	@ResponseStatus(HttpStatus.CREATED)
	public String login(@RequestBody UserCredentials userCredentials) throws SystemException, LoginException {
		return this.loginManager.login(userCredentials);
	}

}