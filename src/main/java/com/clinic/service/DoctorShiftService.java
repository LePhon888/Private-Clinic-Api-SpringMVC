/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.service;

import com.clinic.pojo.DoctorShift;
import java.util.List;

/**
 *
 * @author admin
 */
public interface DoctorShiftService {
    List<DoctorShift> getDoctorShift();
    DoctorShift getDoctorShiftById(int id);
    Boolean createDoctorShift(DoctorShift nurse);
    Boolean updateDoctorShift(DoctorShift nurse);
    Boolean deleteDoctorShift(int id);
}
