/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.repository.impl;

import com.clinic.pojo.Patient;
import com.clinic.pojo.ScheduleDetail;
import com.clinic.pojo.User;
import com.clinic.repository.PatientRepository;
import com.clinic.repository.UserRepository;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author admin
 */
@Repository
@Transactional
public class PatientRepositoryImpl implements PatientRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Boolean createPatient(Map<String, String> patient) {

        Session session = this.factory.getObject().getCurrentSession();

        try {
            String hashedPassword = bCryptPasswordEncoder.encode(patient.get("password"));
            User user = new User();
            user.setName((String) patient.get("name"));
            user.setBirthday((String) patient.get("birthday"));
            user.setEmail((String) patient.get("email"));
            user.setGender((String) patient.get("gender"));
            user.setPhoneNumber((String) patient.get("phoneNumber"));
            user.setAddress((String) patient.get("address"));
            user.setImage((String) patient.get("image"));
            user.setUsername((String) patient.get("username"));
            user.setUserRole("ROLE_PATIENT");
            user.setPassword(hashedPassword);
            session.save(user);
            
            Patient newPatient = new Patient();
            newPatient.setUserId(user);
            session.save(newPatient);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Patient getPatientByUserId(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Patient> q = b.createQuery(Patient.class);
        Root<Patient> root = q.from(Patient.class);
        q.select(root);

        try {
            Predicate predicate = b.equal(root.get("userId"), id);
            q.where(predicate);
        } catch (Exception e) {
        }
        Query<Patient> query = session.createQuery(q);
        return query.getSingleResult();
    }

}
