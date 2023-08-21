/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.controllers;

import com.clinic.pojo.ScheduleDetail;
import com.clinic.service.ScheduleDetailService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author admin
 */
@RestController
@RequestMapping("/api")
public class ApiScheduleDetailController {

    @Autowired
    private ScheduleDetailService scheduleDetailService;

    @GetMapping("/scheduledetails")
    public ResponseEntity<List<ScheduleDetail>> listScheduleDetailByDate(
            @RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.scheduleDetailService.getScheduleDetailByDate(params), HttpStatus.OK);
    }

    @PostMapping("/scheduledetail")
    public ResponseEntity createScheduleDetail(@RequestBody Map<String, Object> schedule) {
        this.scheduleDetailService.createScheduleDetail(schedule);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/scheduledetail/{id}", method = RequestMethod.PATCH)  
    public ResponseEntity<ScheduleDetail> updateIsConfirm(
            @PathVariable int id, @RequestBody Map<String, Short> isConfirm)
    {
        this.scheduleDetailService.updateIsConfirm(
                id, isConfirm);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
