package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
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

public class RatingControllerTests {
    @InjectMocks
    RatingController ratingController;

    @Mock
    RatingRepository ratingRepository;

    @Mock
    Model model;

    @Mock
    BindingResult bindingResult;

    Rating rating;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        rating = new Rating();
        rating.setId(1);
    }

    @Test
    public void testAddRatingForm() {
        String result = ratingController.addRatingForm(rating);
        assertEquals("rating/add", result);
    }

    @Test
    public void testValidate() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(ratingRepository.existsById(rating.getId())).thenReturn(false);
        String result = ratingController.validate(rating, bindingResult, model);
        assertEquals("redirect:/rating/list", result);
    }

    @Test
    public void testShowUpdateForm() {
        when(ratingRepository.findById(rating.getId())).thenReturn(Optional.of(rating));
        String result = ratingController.showUpdateForm(rating.getId(), model);
        assertEquals("rating/update", result);
    }
    @Test
    public void testUpdateRating() {
        when(bindingResult.hasErrors()).thenReturn(false);
        String result = ratingController.updateRating(rating.getId(), rating, bindingResult, model);
        assertEquals("redirect:/rating/list", result);
    }

    @Test
    public void testDeleteRating() {
        when(ratingRepository.findById(rating.getId())).thenReturn(Optional.of(rating));
        String result = ratingController.deleteRating(rating.getId(), model);
        assertEquals("redirect:/rating/list", result);
    }
}
