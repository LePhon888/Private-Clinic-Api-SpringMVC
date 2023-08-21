/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.repository.impl;

import com.clinic.pojo.DoctorShift;
import com.clinic.pojo.NurseShift;
import com.clinic.repository.DoctorShiftRepository;
import java.util.List;
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
public class DoctorShiftRepositoryImpl implements DoctorShiftRepository{

    @Autowired
    private LocalSessionFactoryBean factory;
    
    
    @Override
    public List<DoctorShift> getDoctorShift() {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<DoctorShift> q = b.createQuery(DoctorShift.class);
        Root<DoctorShift> root = q.from(DoctorShift.class);
        q.select(root);    
        Query<DoctorShift> query = session.createQuery(q);
        return query.getResultList();  
    }

    @Override
    public Boolean createDoctorShift(DoctorShift doctorShift) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.save(doctorShift);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }    
    }

    @Override
    public Boolean updateDoctorShift(DoctorShift doctorShift) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.update(doctorShift);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        } 
    }

    @Override
    public Boolean deleteDoctorShift(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        DoctorShift doctorShift = this.getDoctorShiftById(id);
        try {
            s.update(doctorShift);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }    }

    @Override
    public DoctorShift getDoctorShiftById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<DoctorShift> q = b.createQuery(DoctorShift.class);
        Root<DoctorShift> root = q.from(DoctorShift.class);
        q.select(root);

        try {
            Predicate predicate = b.equal(root.get("id"), id);
            q.where(predicate);
        } catch (NumberFormatException e) {
        }
        Query<DoctorShift> query = session.createQuery(q);
        return query.getSingleResult();     
    }
    
}
