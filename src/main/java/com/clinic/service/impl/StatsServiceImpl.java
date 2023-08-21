/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.service.impl;

import com.clinic.repository.StatsRepository;
import com.clinic.service.StatsService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class StatsServiceImpl implements StatsService{

    @Autowired
    private StatsRepository statsRepository;
    
    @Override
    public List<Object[]> statsRevenue(Map<String, String> params) {
        return this.statsRepository.statsRevenue(params);
    }

    @Override
    public List<Object[]> countPatientByTime(Map<String, String> params) {
        return this.statsRepository.countPatientByTime(params);
    }
    
}
