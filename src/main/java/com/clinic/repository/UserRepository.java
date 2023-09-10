/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.repository;

import com.clinic.pojo.User;
import java.util.Map;

/**
 *
 * @author admin
 */
public interface UserRepository {
    User getUserByName(String name);
    User getUserByUsername(String username);
    User getUserByEmail(Map<String, String> params);
    User getUserByPhoneNumber(Map<String, String> params);
    User getUserById(int id);
    User createUser(User user);
    boolean authUser(String username, String password);
    
}