package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
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

public class CurveControllerTests {
    @InjectMocks
    CurveController curveController;

    @Mock
    CurvePointRepository curvePointRepository;

    @Mock
    Model model;

    @Mock
    BindingResult bindingResult;

    CurvePoint curvePoint;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        curvePoint = new CurvePoint();
        curvePoint.setId(1);
    }

    @Test
    public void testAddCurveForm() {
        String result = curveController.addCurveForm(curvePoint);
        assertEquals("curvePoint/add", result);
    }

    @Test
    public void testValidate() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(curvePointRepository.existsById(curvePoint.getId())).thenReturn(false);
        String result = curveController.validate(curvePoint, bindingResult, model);
        assertEquals("redirect:/curvePoint/list", result);
    }

    @Test
    public void testShowUpdateForm() {
        when(curvePointRepository.findById(curvePoint.getId())).thenReturn(Optional.of(curvePoint));
        String result = curveController.showUpdateForm(curvePoint.getId(), model);
        assertEquals("curvePoint/update", result);
    }
    @Test
    public void testUpdateCurve() {
        when(bindingResult.hasErrors()).thenReturn(false);
        String result = curveController.updateCurve(curvePoint.getId(), curvePoint, bindingResult, model);
        assertEquals("redirect:/curvePoint/list", result);
    }
    @Test
    public void testDeleteCurve() {
        when(curvePointRepository.findById(curvePoint.getId())).thenReturn(Optional.of(curvePoint));
        String result = curveController.deleteCurve(curvePoint.getId(), model);
        assertEquals("redirect:/curvePoint/list", result);
    }


}
