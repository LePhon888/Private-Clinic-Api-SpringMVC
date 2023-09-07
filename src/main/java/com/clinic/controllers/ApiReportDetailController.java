/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.controllers;
import com.clinic.pojo.ReportDetail;
import com.clinic.service.ReportDetailService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author admin
 */
@RestController
@RequestMapping("/api")
public class ApiReportDetailController {
    @Autowired
    private ReportDetailService reportDetailService;
    
   @CrossOrigin
   @GetMapping("/report-details/")
    public ResponseEntity<List<ReportDetail>> getReportDetail(
            @RequestParam Map<String, Object> params) {
        return new ResponseEntity<>(this.reportDetailService.getReportDetailByReport(params), HttpStatus.OK);
    }
}
