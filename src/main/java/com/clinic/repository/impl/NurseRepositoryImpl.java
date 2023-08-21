/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.repository.impl;

import com.clinic.pojo.Doctor;
import com.clinic.pojo.Nurse;
import com.clinic.repository.NurseRepository;
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
public class NurseRepositoryImpl implements NurseRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Nurse getNurseById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Nurse> q = b.createQuery(Nurse.class);
        Root<Nurse> root = q.from(Nurse.class);
        q.select(root);

        try {
            Predicate predicate = b.equal(root.get("id"), id);
            q.where(predicate);
        } catch (NumberFormatException e) {
        }
        Query<Nurse> query = session.createQuery(q);
        return query.getSingleResult();
    }

    @Override
    public List<Nurse> getNurses() {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Nurse> q = b.createQuery(Nurse.class);
        Root<Nurse> root = q.from(Nurse.class);
        q.select(root);

        Query<Nurse> query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public Boolean createNurse(Nurse nurse) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.save(nurse);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateNurse(Nurse nurse) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.update(nurse);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteNurse(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Nurse nurse = this.getNurseById(id);
        try {
            s.update(nurse);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
