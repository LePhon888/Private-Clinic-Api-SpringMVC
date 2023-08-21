/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.repository.impl;

import com.clinic.pojo.Department;
import com.clinic.pojo.Doctor;
import com.clinic.pojo.Hour;
import com.clinic.repository.HourRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
public class HourRepositoryImpl implements HourRepository{

    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public List<Hour> getHours() {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Hour> q = b.createQuery(Hour.class);
        Root root = q.from(Hour.class);
        q.select(root);
        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public Hour getHourById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Hour> q = b.createQuery(Hour.class);
        Root<Hour> root = q.from(Hour.class);
        q.select(root);
        try {
            Predicate predicate = b.equal(root.get("id"),id);
            q.where(predicate);
        } catch (Exception e) {
        }
        Query<Hour> query = session.createQuery(q);
        return query.getSingleResult();         
    }
    
}
