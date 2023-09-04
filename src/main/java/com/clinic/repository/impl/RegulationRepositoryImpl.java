/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.repository.impl;

import com.clinic.pojo.Hour;
import com.clinic.pojo.Regulation;
import com.clinic.repository.RegulationRepository;
import java.util.Date;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
public class RegulationRepositoryImpl implements RegulationRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public Boolean createRegulation(Map<String, Double> regulation) {
        Session session = this.factory.getObject().getCurrentSession();

        try {
            Regulation newRegulation = new Regulation();
            newRegulation.setCreatedDate(new Date());
            newRegulation.setFee(regulation.get("fee"));
            newRegulation.setNumberOfPatients(regulation.get("numberOfPatients").intValue()); // Convert to Integer
            session.save(newRegulation);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Regulation getNewRegulation() {
        Session session = this.factory.getObject().getCurrentSession();
        
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Regulation> q = b.createQuery(Regulation.class);
        Root root = q.from(Regulation.class);
        q.select(root);
        
        q.orderBy(b.desc(root.get("id"))); // Assuming "id" is your primary key
        Query query = session.createQuery(q);
        query.setMaxResults(1);
        return (Regulation) query.uniqueResult();    
    }
}
