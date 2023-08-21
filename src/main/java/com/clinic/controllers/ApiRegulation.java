/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.controllers;

import com.clinic.service.RegulationService;
import com.clinic.pojo.Regulation;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.Http2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author admin
 */
@RestController
@RequestMapping("/api")
public class ApiRegulation {

    @Autowired
    private RegulationService regulationService;

    @GetMapping("/new-regulation")
    public ResponseEntity<Regulation> getNewRegulation() {
        return new ResponseEntity<>(this.regulationService.getNewRegulation(), HttpStatus.OK);
    }

    @PostMapping("/regulation")
    public ResponseEntity createRegulation(@RequestBody Map<String, Double> regulation) {
        this.regulationService.createRegulation(regulation);
        return new ResponseEntity(HttpStatus.CREATED);

    }
}
