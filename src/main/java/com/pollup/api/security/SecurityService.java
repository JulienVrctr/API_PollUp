package com.pollup.api.security;

import com.pollup.api.model.Artist;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Transactional(readOnly = true)
public interface SecurityService {
    boolean isAuthenticated();
    Artist getUserLogged();
    void autoLogin(String email, String password, HttpServletRequest request);
}