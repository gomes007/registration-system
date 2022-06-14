package com.semparar.registration.service;


import com.semparar.registration.model.User;

public interface AuthenticationService
{

    User signInReturnJWT(User signInRequest);

    String forgotPassword(User userDTO);
}



