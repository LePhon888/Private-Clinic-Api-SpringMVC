/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.repository.impl;

import com.clinic.pojo.Bill;
import com.clinic.pojo.MedicalReport;
import com.clinic.pojo.ReportDetail;
import com.clinic.pojo.Medicine;
import com.clinic.pojo.Regulation;
import com.clinic.repository.StatsRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
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
public class StatsRepositoryImpl implements StatsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private SimpleDateFormat f;

    @Override
    public List<Object[]> countPatientByTime(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root<MedicalReport> root = q.from(MedicalReport.class);

        List<Expression<Integer>> groupByExpressions = new ArrayList<>();  // Create a list for group by expressions
        List<Predicate> predicates = new ArrayList<>();
        Expression<Integer> expression = null;

        //time is MONTH, QUARTER, YEAR
        String time = params.get("time");
        if (time != null && !time.isEmpty()) {
            expression = b.function(time.toUpperCase(),
                    Integer.class, root.get("createdDate"));
        } else {
            expression = b.function("MONTH",
                    Integer.class, root.get("createdDate"));
        }

        q.multiselect(
                expression,
                b.countDistinct(b.concat(root.get("createdDate"),
                        root.get("patientId")))
        );

        groupByExpressions.add(expression);

        // Set the WHERE clause
        q.where(predicates.toArray(new Predicate[0]));

        // Set the GROUP BY clause
        q.groupBy(groupByExpressions.toArray(new Expression<?>[0]));

        Query<Object[]> query = session.createQuery(q);
        return query.getResultList();
    }

//    @Override
//    public List<Object[]> statsRevenue(Map<String, String> params) {
//        Session session = this.factory.getObject().getCurrentSession();
//        CriteriaBuilder b = session.getCriteriaBuilder();
//        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
//        Root<MedicalReport> medicalReportRoot = q.from(MedicalReport.class);
//        Root<Bill> billRoot = q.from(Bill.class);
//        Root<ReportDetail> reportDetailRoot = q.from(ReportDetail.class);
//        Root<Medicine> medicineRoot = q.from(Medicine.class);
//        
//        q.where(b.equal(medicalReportRoot.get("billId"),
//                billRoot.get("id")));
//        q.where(b.equal(reportDetailRoot.get("medicalReportId"),
//                reportDetailRoot.get("id")));
//        q.where(b.equal(reportDetailRoot.get("medicineId"),
//                medicineRoot.get("id")));
//
//        List<Expression<Integer>> groupByExpressions = new ArrayList<>();  // Create a list for group by expressions
//        List<Predicate> predicates = new ArrayList<>();
//        Expression<Integer> expression = null;
//        
//        //time is MONTH, QUARTER, YEAR
//        String time = params.get("time");
//        if(time != null && !time.isEmpty()) {
//            expression = b.function(time.toUpperCase(), 
//                Integer.class, medicalReportRoot.get("createdDate"));
//        }
//        else {
//            expression = b.function("MONTH", 
//                Integer.class, medicalReportRoot.get("createdDate"));      
//        }
//        
//        q.multiselect(
//                expression,
//                b.sum(b.sum(b.prod(reportDetailRoot.get("quantity"),
//                        medicineRoot.get("unitPrice"))), ));
//
//        groupByExpressions.add(expression);
//
//
//        // Set the WHERE clause
//        q.where(predicates.toArray(new Predicate[0]));
//
//        // Set the GROUP BY clause
//        q.groupBy(groupByExpressions.toArray(new Expression<?>[0]));
//
//        Query<Object[]> query = session.createQuery(q);
//        return query.getResultList();
//    }
    @Override
    public List<Object[]> statsRevenue(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<MedicalReport> root = query.from(MedicalReport.class);
        
        Join<MedicalReport, ReportDetail> reportDetailJoin = root.join("reportDetailCollection");
        Join<ReportDetail, Medicine> medicineJoin = reportDetailJoin.join("medicineId");
        Join<MedicalReport, Bill> billJoin = root.join("billId");
        Join<Bill, Regulation> regulationJoin = billJoin.join("regulationId");

        Expression<Integer> monthExpression = cb.function("MONTH", Integer.class, root.get("createdDate"));

         Expression<Double> revenueExpression = cb.sum(
            cb.prod(
                medicineJoin.get("unitPrice").as(Double.class),  // Cast to Double
                reportDetailJoin.get("quantity").as(Double.class)  // Cast to Double
            ),
            regulationJoin.get("fee").as(Double.class)
    );

        query.multiselect(
                monthExpression.alias("month"),
                cb.sum(revenueExpression).alias("revenue")
        );

        query.groupBy(monthExpression);

        Query<Object[]> hibernateQuery = session.createQuery(query);
        return hibernateQuery.getResultList();
    }

}
