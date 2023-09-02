/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.service.impl;

import com.clinic.repository.BillRepository;
import com.clinic.service.BillService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class BillServiceImpl implements BillService{

    @Autowired
    private BillRepository billRepository;
    
    @Override
    public List<Object[]> getBillByMedicalReportId(Map<String, String> params) {
        return this.billRepository.getBillByMedicalReportId(params);
    }

    @Override
    public List<Object[]> getMedicineBillByMedicalReportId(Map<String, String> params) {
        return this.billRepository.getMedicineBillByMedicalReportId(params);
    }
    
}
