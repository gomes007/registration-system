package com.semparar.registration.service;


import com.semparar.registration.model.User;
import com.semparar.registration.repository.UserRepository;
import com.semparar.registration.security.UserPrinciple;
import com.semparar.registration.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.Charset;
import java.util.Optional;
import java.util.Random;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User signInReturnJWT(User signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
        );

        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(userPrinciple);

        User signInUser = userPrinciple.getUser();
        signInUser.setToken(jwt);

        return signInUser;

    }



    public String forgotPassword(User userDTO) {
        Optional<User> checkIfHas = Optional.ofNullable(userRepository.findByUsername(userDTO.getUsername()));
        String emailTemplate = "<div style=\"height: 100%;display: flex;align-items: center;justify-content: center;background-color: #59b7b7;background-size: auto;\"> <div style=\" width: 640px; margin: 40px auto; padding: 40px; background: #fff; box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19); text-align: center;\"> <h1 style=\"font-family: sans-serif;\">Recuperação de Senha</h1> <h3 style=\"font-family: sans-serif; font-weight: 100;\">username, clique no botão abaixo para alterar sua senha: </h3> <div style=\"display: block; align-items: center; justify-content: center;cursor: pointer;\"> <a href=\"linkapi\"> <div style=\"background-color: #3f51b5; border: none; color: white; padding: 15px 32px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px;cursor: pointer; border-radius: 10px 10px 10px; box-shadow: 0 3px 1px -2px rgba(0,0,0,.2), 0 2px 2px 0 rgba(0,0,0,.14), 0 1px 5px 0 rgba(0,0,0,.12);font-family: sans-serif; font-weight: 100; \">Recuperar Senha</div> </a> </div></div></div>";
        if(checkIfHas.isPresent()){
            checkIfHas.ifPresent(user -> {
                MimeMessage msg = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = null;
                try {
                    helper = new MimeMessageHelper(msg, true);
                    helper.setTo(userDTO.getUsername());
                    helper.setSubject("Recuperação de senha de sua conta " + user.getName());
                    String template = emailTemplate
                            .replace("username", user.getName())
                            .replace("linkapi","http://localhost:3000/recovery?"+"&username="+userDTO.getUsername());
                    helper.setText(template, true);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                javaMailSender.send(msg);
            }); return "Email de recuperação enviado com sucesso.";
        }else{
            return "emailError";
        }
    }



    public String forgotPasswordSecret(User userDTO) {
        Optional<User> checkIfHas = Optional.ofNullable(userRepository.findByUsername(userDTO.getUsername()));
        String emailTemplate = "<div style=\"height: 100%;display: flex;align-items: center;justify-content: center;background-color: #59b7b7;background-size: auto;\"> <div style=\" width: 640px; margin: 40px auto; padding: 40px; background: #fff; box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19); text-align: center;\"> <h1 style=\"font-family: sans-serif;\">Recuperação de Senha</h1> <h3 style=\"font-family: sans-serif; font-weight: 100;\">username, clique no botão abaixo para alterar sua senha: </h3> <div style=\"display: block; align-items: center; justify-content: center;cursor: pointer;\"> <a href=\"linkapi\"> <div style=\"background-color: #3f51b5; border: none; color: white; padding: 15px 32px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px;cursor: pointer; border-radius: 10px 10px 10px; box-shadow: 0 3px 1px -2px rgba(0,0,0,.2), 0 2px 2px 0 rgba(0,0,0,.14), 0 1px 5px 0 rgba(0,0,0,.12);font-family: sans-serif; font-weight: 100; \">Recuperar Senha</div> </a> </div></div></div>";

        String secret = getRandomString(5);

        if(checkIfHas.isPresent()){
            User signUser = checkIfHas.get();
            signUser.setSecret(secret);
            userRepository.save(signUser);

             return "http://localhost:3000/recovery?secret="+secret+"&username="+userDTO.getUsername();
        }else{
            return "emailError";
        }
    }




    @Override
    public String passwordChange(User user) {
        User userFound = userRepository.findByUsername(user.getUsername());
        if (user.getSecret()==null){
            return "missing secret";
        }
        if (user.getSecret().equals(userFound.getSecret())){
            userFound.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(userFound);
            return "password changed!";
        }
        return "incorrect secret!";
    }





    static String getRandomString(int i) {

        // bind the length
        byte[] bytearray;
        bytearray = new byte[256];
        String mystring;
        StringBuffer thebuffer;
        String theAlphaNumericS;

        new Random().nextBytes(bytearray);

        mystring
                = new String(bytearray, Charset.forName("UTF-8"));

        thebuffer = new StringBuffer();

        //remove all spacial char
        theAlphaNumericS
                = mystring
                .replaceAll("[^A-Z0-9]", "");

        //random selection
        for (int m = 0; m < theAlphaNumericS.length(); m++) {

            if (Character.isLetter(theAlphaNumericS.charAt(m))
                    && (i > 0)
                    || Character.isDigit(theAlphaNumericS.charAt(m))
                    && (i > 0)) {

                thebuffer.append(theAlphaNumericS.charAt(m));
                i--;
            }
        }

        // the resulting string
        return thebuffer.toString();
    }





}
