package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

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
public class CurveController {
    // TODO: Inject Curve Point service
    @Autowired
    private CurvePointRepository curvePointRepository;

    @RequestMapping("/curvePoint/list")
    public String home(Model model, HttpServletRequest request) {
        // TODO: find all Curve Point, add to model
        model.addAttribute("curvePoints", curvePointRepository.findAll());
        model.addAttribute("httpServletRequest", request);
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Curve list
        if (!result.hasErrors()) {
            if (curvePoint.getId() != null && curvePointRepository.existsById(curvePoint.getId())) {
                model.addAttribute("error", "curvePoint with id " + curvePoint.getId() + " already exists");
                return "curvePoint/add";
            }
            curvePointRepository.save(curvePoint);
            model.addAttribute("curvePoint", curvePointRepository.findAll());
            return "redirect:/curvePoint/list";
        }
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get CurvePoint by Id and to model then show to the form
        CurvePoint curvePoint = curvePointRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
            BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Curve and return
        if (result.hasErrors()) {
            return "curvePoint/update";
        }
        curvePointRepository.save(curvePoint);
        model.addAttribute("curvePoint", curvePointRepository.findAll());
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Curve by Id and delete the Curve, return to Curve list
        CurvePoint curvePoint = curvePointRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
        curvePointRepository.deleteById(id);
        model.addAttribute("curvePoint", curvePointRepository.findAll());
        return "redirect:/curvePoint/list";
    }
}
