/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.repository.impl;

import com.clinic.pojo.UnitMedicine;
import com.clinic.repository.UnitMedicineRepository;
import java.util.List;
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
public class UnitMedicineRepositoryImpl implements UnitMedicineRepository{

    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public List<UnitMedicine> getAllUnitMedicine() {
         Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<UnitMedicine> q = b.createQuery(UnitMedicine.class);
        Root root = q.from(UnitMedicine.class);
        q.select(root);
        Query query = session.createQuery(q);
        return query.getResultList();
    }
    
}
