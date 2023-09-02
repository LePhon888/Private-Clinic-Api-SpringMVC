package com.clinic.controllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.clinic.service.StatsService;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author admin
 */
@Controller
public class StatController {

    @Autowired
    private StatsService statsService;

    @GetMapping("/patient-stat")
    public String list(Model model, Principal user, @RequestParam Map<String, String> params) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                // Check for a specific role, e.g., "ROLE_ADMIN"
                if (authority.getAuthority().equals("ROLE_ADMIN")) {
                    List<Object[]> patientResult = this.statsService.countPatientByTime(params);
                    model.addAttribute("patientData", patientResult);
                    return "patientstat";
                }
            }
        }

        return "index";
    }
    
     @GetMapping("/revenue-stat")
    public String revenue(Model model, Principal user, @RequestParam Map<String, String> params) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                // Check for a specific role, e.g., "ROLE_ADMIN"
                if (authority.getAuthority().equals("ROLE_ADMIN")) {
                    List<Object[]> feeResult = this.statsService.feeRevenue(params);
                    model.addAttribute("feeData", feeResult);
                    
                    List<Object[]> medicineResult = this.statsService.medicineRevenue(params);
                    model.addAttribute("medicineData", medicineResult);
                    return "revenuestat";
                }
            }
        }

        return "index";
    }

}
