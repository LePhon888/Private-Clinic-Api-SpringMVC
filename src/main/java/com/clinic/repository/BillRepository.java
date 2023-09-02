/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.repository;

import com.clinic.pojo.Bill;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public interface BillRepository {
    List<Object[]> getBillByMedicalReportId(Map<String, String> params);
    List<Object[]> getMedicineBillByMedicalReportId(Map<String, String> params);
}
