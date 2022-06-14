package com.semparar.registration.service;

import com.semparar.registration.model.Role;
import com.semparar.registration.model.User;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service("userService")
public interface UserService {

    User saveUser(User user);

    public Optional<User> findByUsername(String username);

    public Optional<User> findUserByResetToken(String resetToken);


    void changeRole(Role newRole, String username);

}
