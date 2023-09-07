/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.service.impl;

import com.clinic.pojo.ReportDetail;
import com.clinic.repository.ReportDetailRepository;
import com.clinic.service.ReportDetailService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
public class ReportDetailServiceImpl implements ReportDetailService {
    @Autowired
    private ReportDetailRepository reportDetailRepository;
    
    @Override
    public List<ReportDetail> getReportDetailByReport(Map<String, Object> params) {
        return this.reportDetailRepository.getReportDetailByReport(params);
    }
    
}
