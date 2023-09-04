/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.service.impl;

import com.clinic.pojo.ScheduleDetail;
import com.clinic.repository.ScheduleDetailRepository;
import com.clinic.service.ScheduleDetailService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class ScheduleDetailServiceImpl implements ScheduleDetailService{

    @Autowired
    private ScheduleDetailRepository scheduleDetailRepo;
    
    @Override
    public List<ScheduleDetail> getScheduleDetailByDate(Map<String, String> params) {
        return this.scheduleDetailRepo.getScheduleDetailByDate(params);
    }

    @Override
    public Boolean createScheduleDetail(Map<String, Object> schedule) {
        return this.scheduleDetailRepo.createScheduleDetail(schedule);
    }

    @Override
    public ScheduleDetail getScheduleDetailById(int id) {
        return this.scheduleDetailRepo.getScheduleDetailById(id);
    }

    @Override
    public Boolean updateIsConfirm(int scheduleDetailId, Map<String, Short> isConfirm) {
        return this.scheduleDetailRepo.updateIsConfirm(scheduleDetailId, isConfirm);
    }

    @Override
    public Integer countScheduleDetailByDate(Map<String, String> params) {
        return this.scheduleDetailRepo.countScheduleDetailByDate(params);
    }

    @Override
    public Boolean updateIsCancel(int scheduleDetailId, Map<String, Short> isCancel) {
        return this.scheduleDetailRepo.updateIsCancel(scheduleDetailId, isCancel);
    }

   
}
