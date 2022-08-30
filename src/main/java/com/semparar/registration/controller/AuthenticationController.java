package com.semparar.registration.controller;

import com.semparar.registration.model.User;
import com.semparar.registration.service.AuthenticationService;
import com.semparar.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody User user) {

        if (userService.findByUsername(user.getUsername()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody User user){
        return new ResponseEntity<>(authenticationService.signInReturnJWT(user), HttpStatus.OK);
    }


    @PostMapping("/forgot")
    public  ResponseEntity<String> forgotPassword(@RequestBody User userDTO) {
        String response = authenticationService.forgotPassword(userDTO);
        if (response.equals("emailError")){
            return ResponseEntity.status(400).body("Email não cadastrado.");
        }
        else{
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/password-change")
    public  ResponseEntity<String> passwordChange(@RequestBody User user) {
        String response = authenticationService.passwordChange(user);
        return ResponseEntity.ok(response);
    }


}
