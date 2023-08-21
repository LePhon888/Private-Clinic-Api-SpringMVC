/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.repository;

import com.clinic.pojo.Department;
import java.util.List;

/**
 *
 * @author admin
 */
public interface DepartmentRepository {
    List<Department> getDepartments();
    Department getDepartmentById(int id);
}
