package com.nnk.springboot.controllers;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
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
public class RuleNameControllerTests {
    @InjectMocks
    RuleNameController ruleNameController;
    @Mock
    RuleNameRepository ruleNameRepository;
    @Mock
    Model model;
    @Mock
    BindingResult bindingResult;
    RuleName ruleName;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        ruleName = new RuleName();
        ruleName.setId(1);
    }
    @Test
    public void testAddRuleForm() {
        String result = ruleNameController.addRuleForm(ruleName);
        assertEquals("ruleName/add", result);
    }
    @Test
    public void testValidate() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(ruleNameRepository.existsById(ruleName.getId())).thenReturn(false);
        String result = ruleNameController.validate(ruleName, bindingResult, model);
        assertEquals("redirect:/ruleName/list", result);
    }
    @Test
    public void testShowUpdateForm() {
        when(ruleNameRepository.findById(ruleName.getId())).thenReturn(Optional.of(ruleName));
        String result = ruleNameController.showUpdateForm(ruleName.getId(), model);
        assertEquals("ruleName/update", result);
    }
    @Test
    public void testUpdateRule() {
        when(bindingResult.hasErrors()).thenReturn(false);
        String result = ruleNameController.updateRuleName(ruleName.getId(), ruleName, bindingResult, model);
        assertEquals("redirect:/ruleName/list", result);
    }
    @Test
    public void testDeleteRule() {
        when(ruleNameRepository.findById(ruleName.getId())).thenReturn(Optional.of(ruleName));
        String result = ruleNameController.deleteRuleName(ruleName.getId(), model);
        assertEquals("redirect:/ruleName/list", result);
    }
}
