/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.service;

import com.clinic.pojo.Nurse;
import java.util.List;

/**
 *
 * @author admin
 */
public interface NurseService {
    Nurse getNurseById(int id);
    List<Nurse> getNurses();
    Boolean createNurse(Nurse nurse);
    Boolean updateNurse(Nurse nurse);
    Boolean deleteNurse(int id);
}
