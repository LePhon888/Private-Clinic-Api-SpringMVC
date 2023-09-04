/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.service;

import com.clinic.pojo.Patient;
import java.util.Map;

/**
 *
 * @author admin
 */
public interface PatientService {
    Boolean createPatient(Map<String, String> patient);
    Patient getPatientByUserId(int id);
}
