package com.test.app.services;

import org.springframework.transaction.annotation.Transactional;

import com.test.app.model.Login;

public interface LoginService {
	
	@Transactional(readOnly = true)
    Login acceso(String usuario, String password);
}