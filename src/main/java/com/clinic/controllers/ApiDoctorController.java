/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.controllers;

import com.clinic.service.DoctorService;
import com.clinic.pojo.Doctor;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class ApiDoctorController {
    @Autowired
    @Qualifier("doctorServiceImpl")
    private DoctorService doctorService;
    
    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> listDoctorByDepartmentId(
                @RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.doctorService.getDoctorByDepartmentId(params), 
                HttpStatus.OK);
    }
}
