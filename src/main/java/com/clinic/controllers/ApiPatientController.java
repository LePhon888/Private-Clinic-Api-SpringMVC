/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.controllers;

import com.clinic.pojo.Patient;
import com.clinic.pojo.ScheduleDetail;
import com.clinic.service.PatientService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author admin
 */
@RestController
@RequestMapping("/api")
public class ApiPatientController {
    
    @Autowired
    private PatientService patientService;
    
    @PostMapping("/register-patient")
    public ResponseEntity createPatient(@RequestBody Map<String, String> patient) {
        this.patientService.createPatient(patient);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    @GetMapping("/patient/{id}")
    public ResponseEntity<Patient> getPatientById(
            @PathVariable int id) {
        return new ResponseEntity<>(
                this.patientService.getPatientByUserId(id), 
                HttpStatus.OK);
    }
    
    @GetMapping("/patient/")
    @CrossOrigin
     public ResponseEntity<List<Patient>> getPatient(@RequestParam Map<String, Object> params) {
        return new ResponseEntity<>(this.patientService.getAllPatients(params), HttpStatus.OK);
    }
}
