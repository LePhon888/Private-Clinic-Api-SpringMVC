/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.repository.impl;

import com.clinic.pojo.MedicineUnit;
import com.clinic.repository.MedicineUnitRepository;
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
public class MedicineUnitRepositoryImpl implements MedicineUnitRepository{

    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public List<MedicineUnit> getAllMedicineUnit() {
         Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<MedicineUnit> q = b.createQuery(MedicineUnit.class);
        Root root = q.from(MedicineUnit.class);
        q.select(root);
        Query query = session.createQuery(q);
        return query.getResultList();
    }
    
}
