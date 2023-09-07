/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.repository;

import com.clinic.pojo.Medicine;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public interface MedicineRepository {
    List<Medicine> getAllMedicine(Map<String, String> params);
    Medicine getMedicineById(int id);
    Boolean createMedicine(Medicine medicine);
    Boolean updateMedicine(Medicine medicine);
    Boolean deleteMedicine(int id);
}
