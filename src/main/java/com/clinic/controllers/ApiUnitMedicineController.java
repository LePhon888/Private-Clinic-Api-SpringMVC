/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.controllers;

import com.clinic.pojo.UnitMedicine;
import com.clinic.service.UnitMedicineService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.Http2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author admin
 */
@RestController
@RequestMapping("/api")
public class ApiUnitMedicineController {
    @Autowired
    private UnitMedicineService unitMedicineService;
    @GetMapping("/unit-medicine")
    public ResponseEntity<List<UnitMedicine>> getAllUnitMedicine() {
        return new ResponseEntity<>(this.unitMedicineService.getAllUnitMedicine(), HttpStatus.OK);
    }
}
