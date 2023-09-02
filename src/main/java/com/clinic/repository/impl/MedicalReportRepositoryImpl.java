/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.repository.impl;

import com.clinic.pojo.MedicalReport;
import com.clinic.repository.MedicalReportRepository;
import freemarker.template.SimpleDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
// ... imports and annotations ...
@Repository
@Transactional
public class MedicalReportRepositoryImpl implements MedicalReportRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private SimpleDateFormat f;

    @Override
    public List<MedicalReport> getMedicalReports(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<MedicalReport> q = b.createQuery(MedicalReport.class);
        Root<MedicalReport> root = q.from(MedicalReport.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String idString = params.get("id");
            if (idString != null) {
                String[] ids = idString.split(",");
                List<Integer> idList = new ArrayList<>();
                for (String itemId : ids) {
                    idList.add(Integer.parseInt(itemId));
                }
                Predicate patientIdPredicate = root.get("id").in(idList);
                predicates.add(patientIdPredicate);
            }

            String fromDate = params.get("fromDate");
            String toDate = params.get("toDate");
            if ((fromDate != null && !fromDate.isEmpty())
                    && (toDate != null && !toDate.isEmpty())) {

                try {
                    Date parsedFromDate = f.parse(fromDate);
                    Date parsedToDate = f.parse(toDate);

                    Predicate dateRangePredicate = b.between(root.get("createdDate"), parsedFromDate, parsedToDate);
                    predicates.add(dateRangePredicate);
                } catch (ParseException ex) {
                    Logger.getLogger(MedicalReportRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            String patientId = params.get("patientId");
            if (patientId != null) {
                String[] patientIds = patientId.split(",");
                List<Integer> parsedPatientIds = new ArrayList<>();
                for (String id : patientIds) {
                    parsedPatientIds.add(Integer.parseInt(id));
                }
                Predicate patientIdPredicate = root.get("patientId").in(parsedPatientIds);
                predicates.add(patientIdPredicate);
            }

            String isPaid = params.get("isPaid");
            if (isPaid != null) {
                Predicate isPaidPredicate = b.equal(
                        root.get("isPaid"),
                        Integer.parseInt(isPaid));
                predicates.add(isPaidPredicate);
            }

            q.where(predicates.toArray(new Predicate[0]));

        }
        Query<MedicalReport> query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public MedicalReport getMedicalReportById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<MedicalReport> q = b.createQuery(MedicalReport.class);
        Root<MedicalReport> root = q.from(MedicalReport.class);
        q.select(root);
        try {
            Predicate predicate1 = b.equal(root.get("id"), id);
            q.where(predicate1);
        } catch (Exception ex) {
            Logger.getLogger(MedicalReportRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        Query<MedicalReport> query = session.createQuery(q);
        return query.getSingleResult();
    }

    @Override
    public Boolean updatePaid(Map<String, String> params) {
    Session session = this.factory.getObject().getCurrentSession();
    
    try {
        List<Integer> idList = new ArrayList<>();
        String idString = params.get("id");
        
        if (idString != null) {
            String[] idStrings = idString.split(",");
            
            for (String id : idStrings) {
                idList.add(Integer.parseInt(id));
            }
            
            for (int id : idList) {
                MedicalReport updatedMedicalReport = this.getMedicalReportById(id);
                
                if (updatedMedicalReport != null) {
                    Short isPaid = 1;
                    updatedMedicalReport.setIsPaid(isPaid);
                    session.update(updatedMedicalReport);
                } else {
                    System.err.println("Can not found medical report");
                }
            }
            
            return true;  
        }
    } catch (NumberFormatException e) {
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    return false;  
}


}
