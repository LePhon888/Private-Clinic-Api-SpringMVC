/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.controllers;

import com.clinic.service.StatsService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author admin
 */
@RestController
@RequestMapping("/api")
public class ApiStatsController {
    @Autowired
    private StatsService statsService;

    @GetMapping("/count-patient")
    public ResponseEntity<List<Object[]>> statsPatient(@RequestParam Map<String, String> params) {
        List<Object[]> result = statsService.countPatientByTime(params);

        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
    @GetMapping("/fee-revenue")
    public ResponseEntity<List<Object[]>> feeRevenue(@RequestParam Map<String, String> params) {
        List<Object[]> result = statsService.feeRevenue(params);

        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
    @GetMapping("/medicine-revenue")
    public ResponseEntity<List<Object[]>> medicineRevenue(@RequestParam Map<String, String> params) {
        List<Object[]> result = statsService.medicineRevenue(params);

        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
}

