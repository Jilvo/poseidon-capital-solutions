package com.nnk.springboot.controllers;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
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
public class TradeControllerTests {
    @InjectMocks
    TradeController tradeController;
    @Mock
    TradeRepository tradeRepository;
    @Mock
    Model model;
    @Mock
    BindingResult bindingResult;
    Trade trade;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        trade = new Trade();
        trade.setId(1);
    }
    @Test
    public void testAddTradeForm() {
        String result = tradeController.addTradeForm(trade);
        assertEquals("trade/add", result);
    }
    @Test
    public void testValidate() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(tradeRepository.existsById(trade.getId())).thenReturn(false);
        String result = tradeController.validate(trade, bindingResult, model);
        assertEquals("redirect:/trade/list", result);
    }
    @Test
    public void testShowUpdateForm() {
        when(tradeRepository.findById(trade.getId())).thenReturn(Optional.of(trade));
        String result = tradeController.showUpdateForm(trade.getId(), model);
        assertEquals("trade/update", result);
    }
    @Test
    public void testUpdateTrade() {
        when(bindingResult.hasErrors()).thenReturn(false);
        String result = tradeController.updateTrade(trade.getId(), trade, bindingResult, model);
        assertEquals("redirect:/trade/list", result);
    }
    @Test
    public void testDeleteTrade() {
        when(tradeRepository.findById(trade.getId())).thenReturn(Optional.of(trade));
        String result = tradeController.deleteTrade(trade.getId(), model);
        assertEquals("redirect:/trade/list", result);
    }
}
