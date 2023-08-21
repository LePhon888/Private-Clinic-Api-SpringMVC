/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.service.impl;

import com.clinic.pojo.Doctor;
import com.clinic.repository.DepartmentRepository;
import com.clinic.repository.DoctorRepository;
import com.clinic.service.DoctorService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class DoctorServiceImpl implements DoctorService{

    @Autowired
    private DoctorRepository doctorRepository;
    
    @Override
    public List<Doctor> getDoctorByDepartmentId(Map<String, String> params) {
        return this.doctorRepository.getDoctorByDepartmentId(params);
    }
    
}
