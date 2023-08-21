/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.clinic.repository;

import com.clinic.pojo.Category;
import java.util.List;

/**
 *
 * @author admin
 */
public interface CategoryRepository {
    List<Category> getCategories();
}
