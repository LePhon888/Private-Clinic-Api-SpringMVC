/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.service.impl;

import com.clinic.pojo.Hour;
import com.clinic.repository.HourRepository;
import com.clinic.service.HourService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class HourServiceImpl implements HourService{
    @Autowired
    private HourRepository hourRepository;

    @Override
    public List<Hour> getHours() {
        return this.hourRepository.getHours();
    }
    
    
}
