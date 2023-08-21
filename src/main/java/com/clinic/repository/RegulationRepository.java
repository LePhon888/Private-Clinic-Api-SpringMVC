/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.repository;

import com.clinic.pojo.Regulation;
import java.util.Map;

/**
 *
 * @author admin
 */
public interface RegulationRepository {
    Boolean createRegulation(Map<String, Double> regulation);
    Regulation getNewRegulation();
}
