/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.controllers;

import com.clinic.service.BillService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author admin
 */
@RestController
@RequestMapping("/api")
public class ApiBillController {
    
    @Autowired
    private BillService billService;
    
    @GetMapping("/bill")
    public List<Object[]> getBillByMedicalReportId(
            @RequestParam Map<String, String> params) {
        return this.billService.getBillByMedicalReportId(params);
    }
    
     @GetMapping("/medicine-bill")
    public List<Object[]> getMedicineBillByMedicalReportId(
            @RequestParam Map<String, String> params) {
        return this.billService.getMedicineBillByMedicalReportId(params);
    }
}
