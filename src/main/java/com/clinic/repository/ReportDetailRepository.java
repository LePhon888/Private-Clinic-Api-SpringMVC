/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.repository;

import com.clinic.pojo.ReportDetail;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hp
 */
public interface ReportDetailRepository {
    List<ReportDetail> getReportDetailByReport(Map<String, Object> params);
}
