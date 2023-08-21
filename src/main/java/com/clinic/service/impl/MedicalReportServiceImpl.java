/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.service.impl;

import com.clinic.pojo.MedicalReport;
import com.clinic.repository.MedicalReportRepository;
import com.clinic.service.MedicalReportService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class MedicalReportServiceImpl implements MedicalReportService{
    @Autowired
    private MedicalReportRepository medicalReportRepository;

    @Override
    public List<MedicalReport> getMedicalReports(Map<String, String> params) {
        return this.medicalReportRepository.getMedicalReports(params);
    }
}
