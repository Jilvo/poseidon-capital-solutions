package com.nnk.springboot.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindingResult;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.ui.Model;
import java.util.Optional;

public class BidListControllerTests {
    @InjectMocks
    BidListController bidListController;

    @Mock
    BidListRepository bidListRepository;

    @Mock
    Model model;

    @Mock
    BindingResult bindingResult;

    BidList bidList;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        bidList = new BidList();
        bidList.setId(1);
    }

    @Test
    public void testAddBidForm() {
        String result = bidListController.addBidForm(bidList);
        assertEquals("bidList/add", result);
    }

    @Test
    public void testValidate() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(bidListRepository.existsById(bidList.getId())).thenReturn(false);
        String result = bidListController.validate(bidList, bindingResult, model);
        assertEquals("redirect:/bidList/list", result);
    }

    @Test
    public void testShowUpdateForm() {
        when(bidListRepository.findById(bidList.getId())).thenReturn(Optional.of(bidList));
        String result = bidListController.showUpdateForm(bidList.getId(), model);
        assertEquals("bidList/update", result);
    }

    @Test
    public void testUpdateBid() {
        when(bindingResult.hasErrors()).thenReturn(false);
        String result = bidListController.updateBid(bidList.getId(), bidList, bindingResult, model);
        assertEquals("redirect:/bidList/list", result);
    }

    @Test
    public void testDeleteBid() {
        when(bidListRepository.findById(bidList.getId())).thenReturn(Optional.of(bidList));
        String result = bidListController.deleteBid(bidList.getId(), model);
        assertEquals("redirect:/bidList/list", result);
    }
}
