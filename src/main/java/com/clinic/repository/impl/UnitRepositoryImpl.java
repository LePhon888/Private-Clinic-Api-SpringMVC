/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.repository.impl;

import com.clinic.pojo.Unit;
import com.clinic.repository.UnitRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
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
public class UnitRepositoryImpl implements UnitRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Unit getUnitById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Unit> q = b.createQuery(Unit.class);
        Root<Unit> root = q.from(Unit.class);
        q.select(root);

        try {
            Predicate predicate = b.equal(root.get("id"), id);
            q.where(predicate);

            Query<Unit> query = s.createQuery(q);
            return query.getSingleResult();
        } catch (NoResultException e) {
            // Handle the case when no user is found with the given name
            return null; // or throw a custom exception, return a custom response, etc.
        }
    }

    @Override
    public List<Unit> getUnits(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Unit> q = b.createQuery(Unit.class);
        Root<Unit> root = q.from(Unit.class);
        q.select(root);
        
        if (params != null)
        {
            List<Predicate> predicates = new ArrayList();
            String id = params.get("id");
            if (id != null && !id.isEmpty()) {
                predicates.add(b.equal(root.get("id"), id));
            }
            q.where(predicates.toArray(Predicate[]:: new));
        }
        
        return s.createQuery(q).getResultList();
    }

}