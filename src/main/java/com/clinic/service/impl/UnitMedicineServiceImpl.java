/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.service.impl;

import com.clinic.pojo.UnitMedicine;
import com.clinic.repository.UnitMedicineRepository;
import com.clinic.service.UnitMedicineService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class UnitMedicineServiceImpl implements UnitMedicineService{
    @Autowired
    private UnitMedicineRepository unitMedicineRepository;

    @Override
    public List<UnitMedicine> getAllUnitMedicine() {
        return this.unitMedicineRepository.getAllUnitMedicine();
    }
    
}
