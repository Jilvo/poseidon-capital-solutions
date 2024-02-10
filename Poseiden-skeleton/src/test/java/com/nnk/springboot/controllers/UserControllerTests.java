package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserControllerTests {
    @InjectMocks
    UserController userController;
    @Mock
    UserRepository userRepository;
    @Mock
    Model model;
    @Mock
    BindingResult bindingResult;
    User user;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1);
        user.setUsername("john.doe@example.com");
        user.setPassword("$2a$10$mcPOGPV77LLhooG.2HKSX.7F2.1PjNWdEktSkihIXIaJGS6cYf5Ee");

    }

    @Test
    public void testAddUserForm() {
        String result = userController.addUserForm(user);
        assertEquals("user/add", result);
    }

    @Test
    public void testShowUpdateForm() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        String result = userController.showUpdateForm(user.getId(), model);
        assertEquals("user/update", result);
    }

    @Test
    public void testUpdateUser() {
        when(bindingResult.hasErrors()).thenReturn(false);
        String result = userController.updateUser(user.getId(), user, bindingResult, model);
        assertEquals("redirect:/user/list", result);
    }

    @Test
    public void testDeleteUser() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        String result = userController.deleteUser(user.getId(), model);
        assertEquals("redirect:/user/list", result);
    }
}
