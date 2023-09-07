/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.repository.impl;

import com.clinic.pojo.Department;
import com.clinic.pojo.Doctor;
import com.clinic.repository.DoctorRepository;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author admin
 */
@Repository
@Transactional
public class DoctorRepositoryImpl implements DoctorRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Doctor> getDoctorByDepartmentId(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Doctor> q = b.createQuery(Doctor.class);
        Root<Doctor> root = q.from(Doctor.class);
        q.select(root);

        if (params != null) {
            String departmentId = params.get("departmentId");
            try {
                Predicate predicate = b.equal(root.get("departmentId"), Integer.parseInt(departmentId));
                q.where(predicate);
            } catch (NumberFormatException e) {
                // Handle the invalid departmentId value here if needed
            }
        }
        Query<Doctor> query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public Doctor getDoctorById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Doctor> q = b.createQuery(Doctor.class);
        Root<Doctor> root = q.from(Doctor.class);
        q.select(root);

        try {
            Predicate predicate = b.equal(root.get("id"), id);
            q.where(predicate);
        } catch (NumberFormatException e) {
        }
        Query<Doctor> query = session.createQuery(q);
        return query.getSingleResult();
    }
    
     @Override
    public Doctor getDoctorByUserId(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Doctor> q = b.createQuery(Doctor.class);
        Root<Doctor> root = q.from(Doctor.class);
        q.select(root);

        try {
            Predicate predicate = b.equal(root.get("userId"), id);
            q.where(predicate);
        } catch (Exception e) {
        }
        Query<Doctor> query = session.createQuery(q);
        return query.getSingleResult();
    }

    @Override
    public Boolean createDoctor(Doctor doctor) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.save(doctor);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateDoctor(Doctor doctor) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.update(doctor);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteDoctor(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Doctor doctor = this.getDoctorById(id);
        try {
            s.update(doctor);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
