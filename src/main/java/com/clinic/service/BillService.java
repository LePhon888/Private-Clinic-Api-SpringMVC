/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.service;

import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public interface BillService {

    List<Object[]> getBillByMedicalReportId(Map<String, String> params);

    List<Object[]> getMedicineBillByMedicalReportId(Map<String, String> params);

}
