package com.nnk.springboot.services;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 50;

    public boolean isPasswordLengthValid(String password) {
        return password.length() >= MIN_PASSWORD_LENGTH && password.length() <= MAX_PASSWORD_LENGTH;
    }

    public boolean isPasswordValid(String password) {
        return password.matches(".*[A-Z].*") && password.matches(".*[a-z].*") && password.matches(".*\\d.*")
                && password.matches(".*[!@#$%^&*()].*") && isPasswordLengthValid(password);
    }
}
