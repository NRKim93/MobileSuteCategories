package com.example.ms.controller;

import com.example.ms.repository.MobileSuitRepository;
import com.example.ms.service.MobileSuitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mobileSuit")
@RequiredArgsConstructor
public class RegistrationController {
    private  final MobileSuitService service;
    private  final MobileSuitRepository repository;


    @GetMapping("/register")
    public String showRegistrationForm() {
        return "registration"; // templates/registration.html로 이동
    }

    @PostMapping("/registerMobileSuit")
    public String registerMobileSuit(
            @RequestParam String msId,
            @RequestParam String msName,
            @RequestParam String msType,
            @RequestParam String msStatus,
            @RequestParam double msCost,
            @RequestParam String companyName,
            @RequestParam String factionName
    ) {
        service.addMobileSuit(msId, msName, msType, msStatus, msCost, companyName, factionName);
        return "redirect:/mobileSuit/list";
    }
}
