package com.nnk.springboot.services;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 50;

    private static final String LOWERCASE = ".*[a-z].*";
    private static final String UPPERCASE = ".*[A-Z].*";
    private static final String NUMERICAL = ".*\\d.*";
    private static final String SPECIALCARAC = ".*[!@#$%^&*()].*";


    public boolean isPasswordLengthValid(String password) {
        return password.length() >= MIN_PASSWORD_LENGTH && password.length() <= MAX_PASSWORD_LENGTH;
    }

    public boolean isPasswordValid(String password) {
        return password.matches(UPPERCASE) && password.matches(LOWERCASE) && password.matches(NUMERICAL)
                && password.matches(SPECIALCARAC) && isPasswordLengthValid(password);
    }
}
