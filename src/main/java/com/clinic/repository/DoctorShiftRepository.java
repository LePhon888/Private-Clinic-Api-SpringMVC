/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.repository;

import com.clinic.pojo.DoctorShift;
import java.util.List;

/**
 *
 * @author admin
 */
public interface DoctorShiftRepository {
    List<DoctorShift> getDoctorShift();
    DoctorShift getDoctorShiftById(int id);
    Boolean createDoctorShift(DoctorShift nurse);
    Boolean updateDoctorShift(DoctorShift nurse);
    Boolean deleteDoctorShift(int id);
}
