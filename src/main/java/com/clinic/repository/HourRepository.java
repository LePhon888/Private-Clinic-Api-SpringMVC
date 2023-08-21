/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.repository;

import java.util.List;
import com.clinic.pojo.Hour;

/**
 *
 * @author admin
 */
public interface HourRepository {
    List<Hour> getHours();
    Hour getHourById(int id);
}
