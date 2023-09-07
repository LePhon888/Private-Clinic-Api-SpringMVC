/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.repository;

import com.clinic.pojo.Patient;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public interface PatientRepository {
    Boolean createPatient(Map<String, String> patient);
    List<Patient> getAllPatients(Map<String, Object> params);
    Patient getPatientByUserId(int id);
    Patient getById(int id);
}
