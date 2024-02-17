package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class RatingController {

    @Autowired
    private RatingRepository ratingRepository;

    @RequestMapping("/rating/list")
    public String home(Model model, HttpServletRequest request) {
        model.addAttribute("ratings", ratingRepository.findAll());
        model.addAttribute("httpServletRequest", request);
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            if (rating.getId() != null && ratingRepository.existsById(rating.getId())) {
                model.addAttribute("error", "rating with id " + rating.getId() + " already exists");
                return "rating/add";
            }
            ratingRepository.save(rating);
            model.addAttribute("ratings", ratingRepository.findAll());
            return "redirect:/rating/list";
        }
        return "rating/add";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        model.addAttribute("rating", rating);
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "rating/update";
        }
        ratingRepository.save(rating);
        model.addAttribute("ratings", ratingRepository.findAll());
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        ratingRepository.deleteById(id);
        model.addAttribute("ratings", ratingRepository.findAll());
        return "redirect:/rating/list";
    }
}
