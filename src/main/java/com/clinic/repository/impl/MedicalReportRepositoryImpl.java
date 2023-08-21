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

            String idParam = params.get("id");
            if (idParam != null && !idParam.isEmpty()) {
                int id = Integer.parseInt(idParam);
                predicates.add(b.equal(root.get("id"), id));
            }

            String fromDate = params.get("fromDate");
            String toDate = params.get("toDate");
            if ((fromDate != null && !fromDate.isEmpty())
                    && (toDate != null && !toDate.isEmpty())) {

                try {
                    Date parsedFromDate = f.parse(fromDate);
                    Date parsedToDate = f.parse(toDate);

                    // Adjust the range to include the entire day of fromDate and toDate
                    Predicate dateRangePredicate = b.between(root.get("createdDate"), parsedFromDate, parsedToDate);
                    predicates.add(dateRangePredicate);
                } catch (ParseException ex) {
                    Logger.getLogger(MedicalReportRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                }

                q.where(predicates.toArray(new Predicate[0]));
            }
        }
        Query<MedicalReport> query = session.createQuery(q);
        return query.getResultList();
    }
}
