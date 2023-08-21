/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.service.impl;

import com.clinic.pojo.Regulation;
import com.clinic.repository.RegulationRepository;
import com.clinic.service.RegulationService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class RegulationServiceImpl implements RegulationService{
    @Autowired
    private RegulationRepository regulationRepository;
    
    @Override
    public Boolean createRegulation(Map<String,Double> regulation) {
        return this.regulationRepository.createRegulation(regulation);
    }

    @Override
    public Regulation getNewRegulation() {
        return this.regulationRepository.getNewRegulation();
    }
    
}
