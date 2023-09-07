/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.repository;

import com.clinic.pojo.Doctor;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public interface DoctorRepository {
    List<Doctor> getDoctorByDepartmentId(Map<String, String> params);
    Doctor getDoctorById(int id);
    Doctor getDoctorByUserId(int id);
    Boolean createDoctor(Doctor doctor);
    Boolean updateDoctor(Doctor doctor);
    Boolean deleteDoctor(int id);
}
