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
public interface StatsService {
    List<Object[]> countPatientByTime(Map<String,String> params);
    List<Object[]> feeRevenue(Map<String, String> params);
    List<Object[]> medicineRevenue(Map<String, String> params);
}
