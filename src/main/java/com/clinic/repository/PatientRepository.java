/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.repository;

import java.util.Map;

/**
 *
 * @author admin
 */
public interface PatientRepository {
    Boolean createPatient(Map<String, String> patient);
}
