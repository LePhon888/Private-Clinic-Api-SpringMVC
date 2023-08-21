/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.service.impl;

import com.clinic.pojo.Medicine;
import com.clinic.repository.MedicineRepository;
import com.clinic.service.MedicineService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class MedicineServiceImpl implements MedicineService{

    @Autowired
    private MedicineRepository medicineRepository;
    
    @Override
    public List<Medicine> getAllMedicine() {
        return this.medicineRepository.getAllMedicine();
    }
    
}
