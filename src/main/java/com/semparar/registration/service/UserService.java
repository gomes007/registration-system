package com.semparar.registration.service;

import com.semparar.registration.repository.AddressRepository;
import com.semparar.registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;


}
