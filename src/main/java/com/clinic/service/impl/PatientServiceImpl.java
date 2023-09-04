/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.service.impl;

import com.clinic.pojo.Patient;
import com.clinic.repository.PatientRepository;
import com.clinic.service.PatientService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    private PatientRepository patientRepository;
    
    @Override
    public Boolean createPatient(Map<String, String> patient) {
        return this.patientRepository.createPatient(patient);
    }

    @Override
    public Patient getPatientByUserId(int id) {
        return this.patientRepository.getPatientByUserId(id);
    }
    
}
