package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;

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
public class TradeController {
    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private TradeService tradeService;

    @RequestMapping("/trade/list")
    public String home(Model model, HttpServletRequest request) {
        model.addAttribute("trades", tradeRepository.findAll());
        model.addAttribute("httpServletRequest", request);
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addTradeForm(Trade bid) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            if (trade.getId() != null && tradeRepository.existsById(trade.getId())) {
                model.addAttribute("error", "trade with id " + trade.getId() + " already exists");
                return "trade/add";
            }
            tradeRepository.save(trade);
            model.addAttribute("trades", tradeRepository.findAll());
            return "redirect:/trade/list";
        }
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        model.addAttribute("trade", trade);
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "trade/update";
        }
        tradeRepository.save(trade);
        model.addAttribute("trades", tradeRepository.findAll());
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        tradeRepository.deleteById(id);
        model.addAttribute("trades", tradeRepository.findAll());
        return "redirect:/trade/list";
    }
}
