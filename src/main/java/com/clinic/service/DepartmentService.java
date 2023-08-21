/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.service;

import com.clinic.pojo.Department;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
public interface DepartmentService {
    List<Department> getDepartments();

}
