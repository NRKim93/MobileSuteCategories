package com.example.ms.controller;

import com.example.ms.entity.MobileSuit;
import com.example.ms.repository.MobileSuitRepository;
import com.example.ms.service.MobileSuitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mobileSuit")
@RequiredArgsConstructor
public class MobileSuitController {

    private final MobileSuitService service;
    private final MobileSuitRepository repository;

    @GetMapping("/list")
    public String getMobileSuitList(@RequestParam(value = "type", required = false) String msType, Model model) {
        List<MobileSuit> suits = repository.findAllOrderByStatus();
        model.addAttribute("suitList", suits);
        return "list";
    }

    @GetMapping("/search")
    public String searchMobileSuit(
            @RequestParam(value = "filterType", required = false) String filterType,
            @RequestParam(value = "filterValue", required = false) String filterValue,
            Model model) {
        List<MobileSuit> suits;

        if(filterType == null || filterValue == null ||filterValue.trim().isEmpty()) {
            suits = service.getAllMobileSuits();
        } else {
            suits = service.getFilteredMobileSuits(filterType, filterValue);
        }

        model.addAttribute("suitList", suits);
        return "list";
    }

}
