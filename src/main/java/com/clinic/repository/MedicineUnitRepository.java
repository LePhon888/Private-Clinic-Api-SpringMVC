/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.repository;

import com.clinic.pojo.MedicineUnit;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public interface MedicineUnitRepository {
    List<MedicineUnit> getAllMedicineUnit(Map<String, String> object);
    MedicineUnit getOrCreateByMedicineUnit(int medicineId, int unitId);
    boolean update(MedicineUnit medicineUnit);
}
