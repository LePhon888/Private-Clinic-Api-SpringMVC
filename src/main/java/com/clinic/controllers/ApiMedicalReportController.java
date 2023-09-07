/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.controllers;

import com.clinic.pojo.MedicalReport;
import com.clinic.pojo.ScheduleDetail;
import com.clinic.service.MedicalReportService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author admin
 */
@RestController
@RequestMapping("/api")
public class ApiMedicalReportController {
    @Autowired
    private MedicalReportService medicalReportService;
    
    @CrossOrigin
    @GetMapping("/medical-report")
    public ResponseEntity<List<MedicalReport>> getMedicalReports(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.medicalReportService.getMedicalReports(params), HttpStatus.OK);
    }
    @CrossOrigin
     @RequestMapping(value = "/medical-report", method = RequestMethod.PATCH)  
    public ResponseEntity<ScheduleDetail> updateIsPaid(
            @RequestParam Map<String, String> params)
    {
        this.medicalReportService.updatePaid(params);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @CrossOrigin
    @PostMapping("/medical-report/")
    public ResponseEntity create(@RequestBody Map<String, Object> object) {
        return new ResponseEntity<>(this.medicalReportService.create(object), HttpStatus.CREATED);

    }
}
