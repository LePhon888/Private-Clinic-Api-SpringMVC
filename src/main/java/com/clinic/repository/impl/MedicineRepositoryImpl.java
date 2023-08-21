/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.repository.impl;

import com.clinic.pojo.DoctorShift;
import com.clinic.pojo.Hour;
import com.clinic.pojo.Medicine;
import com.clinic.repository.MedicineRepository;
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
public class MedicineRepositoryImpl implements MedicineRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Medicine> getAllMedicine() {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Medicine> q = b.createQuery(Medicine.class);
        Root root = q.from(Medicine.class);
        q.select(root);
        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public Medicine getMedicineById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Medicine> q = b.createQuery(Medicine.class);
        Root<Medicine> root = q.from(Medicine.class);
        q.select(root);

        try {
            Predicate predicate = b.equal(root.get("id"), id);
            q.where(predicate);
        } catch (NumberFormatException e) {
        }
        Query<Medicine> query = session.createQuery(q);
        return query.getSingleResult();      
    }

    @Override
    public Boolean createMedicine(Medicine medicine) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.save(medicine);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }        }

    @Override
    public Boolean updateMedicine(Medicine medicine) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.update(medicine);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }     }

    @Override
    public Boolean deleteMedicine(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Medicine medicine = this.getMedicineById(id);
        try {
            s.update(medicine);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        } 
    }

}
