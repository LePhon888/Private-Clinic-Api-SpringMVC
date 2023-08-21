/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.service;

import com.clinic.pojo.ScheduleDetail;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public interface ScheduleDetailService {
    List<ScheduleDetail> getScheduleDetailByDate(Map<String, String> params);
    Boolean createScheduleDetail(Map<String, Object> schedule);
    ScheduleDetail getScheduleDetailById(int id);
    Boolean updateIsConfirm(int scheduleDetailId, Map<String, Short> isConfirm);
}
