/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.service.impl;
import com.clinic.pojo.Unit;
import com.clinic.repository.UnitRepository;
import com.clinic.service.UnitService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
public class UnitServiceImpl implements UnitService{
    @Autowired
    private UnitRepository unitRepository;

    @Override
    public List<Unit> getUnits(Map<String, String> params) {
        return this.unitRepository.getUnits(params);
    }
    
}