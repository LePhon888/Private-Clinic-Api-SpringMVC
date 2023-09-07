/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.controllers;

import com.clinic.pojo.Unit;
import com.clinic.service.UnitService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hp
 */
@RestController
@RequestMapping("/api")
public class ApiUnitController {
    @Autowired
    private UnitService unitService;
    
    @CrossOrigin
    @GetMapping("/units/")
    public ResponseEntity<List<Unit>> getUnits(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.unitService.getUnits(params), HttpStatus.OK);
    }
    
}