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
    
    // 초기 화면 표시 
    @GetMapping("/list")
    public String getMobileSuitList(@RequestParam(value = "type", required = false) String msType, Model model) {
        List<MobileSuit> ms = repository.findAllOrderByStatus();
        model.addAttribute("msList", ms);
        return "list";
    }

    // DB에 있는 값들 SELECT 해 온 데이터들 화면에 넘겨주는 기능
    @GetMapping("/search")
    public String searchMobileSuit(
            @RequestParam(value = "filterType", required = false) String filterType,
            @RequestParam(value = "filterValue", required = false) String filterValue,
            Model model) {
        List<MobileSuit> ms;

        if(filterType == null || filterValue == null ||filterValue.trim().isEmpty()) {
            ms = service.getAllMobileSuits();
        } else {
            ms = service.getFilteredMobileSuits(filterType, filterValue);
        }

        model.addAttribute("suitList", ms);
        return "list";
    }

}
