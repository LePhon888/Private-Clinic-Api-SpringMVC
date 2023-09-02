/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.repository.impl;

import com.clinic.pojo.Bill;
import com.clinic.pojo.MedicalReport;
import com.clinic.pojo.ReportDetail;
import com.clinic.pojo.Medicine;
import com.clinic.pojo.MedicineUnit;
import com.clinic.pojo.Regulation;
import com.clinic.repository.StatsRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
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

        List<Expression<Integer>> groupByExpressions = new ArrayList<>(); 
        List<Predicate> predicates = new ArrayList<>();
        Expression<Integer> timeExpression = null;

        //time is MONTH, QUARTER, YEAR
        String time = params.get("time");
        if (time != null && !time.isEmpty()) {
            timeExpression = b.function(time.toUpperCase(),
                    Integer.class, root.get("createdDate"));
        } else {
            timeExpression = b.function("MONTH",
                    Integer.class, root.get("createdDate"));
        }

        q.multiselect(
                timeExpression,
                b.countDistinct(b.concat(root.get("createdDate"),
                        root.get("patientId")))
        );

        groupByExpressions.add(timeExpression);

        // Set the WHERE clause
        q.where(predicates.toArray(new Predicate[0]));

        // Set the GROUP BY clause
        q.groupBy(groupByExpressions.toArray(new Expression<?>[0]));

        Query<Object[]> query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public List<Object[]> feeRevenue(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<MedicalReport> medicalReportRoot = query.from(MedicalReport.class);

        Join<MedicalReport, Bill> billJoin = medicalReportRoot.join("billId", JoinType.LEFT);
        Join<Bill, Regulation> regulationJoin = billJoin.join("regulationId", JoinType.LEFT);

        Expression<Integer> timeExpression = null;

        String time = params.get("time");
        if (time != null && !time.isEmpty()) {
            timeExpression = cb.function(time.toUpperCase(),
                    Integer.class, medicalReportRoot.get("createdDate"));
        } else {
            timeExpression = cb.function("MONTH",
                    Integer.class, medicalReportRoot.get("createdDate"));
        }

        Expression<Double> feeCoalesceExpression = cb.coalesce(regulationJoin.get("fee").as(Double.class), 0.0);
        Expression<Long> countExpression = cb.count(medicalReportRoot.get("id"));

        query.multiselect(
                timeExpression,
                cb.prod(feeCoalesceExpression, countExpression)
        );

        query.groupBy(timeExpression, feeCoalesceExpression);
        query.orderBy(cb.asc(timeExpression));

        List<Object[]> resultList = session.createQuery(query).getResultList();

        return resultList;

    }


    @Override
    public List<Object[]> medicineRevenue(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);

        Root<ReportDetail> reportDetailRoot = query.from(ReportDetail.class);
        
        Join<ReportDetail, MedicalReport> medicalReportJoin
                = reportDetailRoot.join("medicalreportId");
        Join<ReportDetail, MedicineUnit> medicineUnitJoin
                = reportDetailRoot.join("medicineUnitId");

        Expression<Integer> timeExpression = null;
        
        String time = params.get("time");
        if (time != null && !time.isEmpty()) {
            timeExpression = cb.function(
                    time.toUpperCase(),
                    Integer.class,
                    medicalReportJoin.get("createdDate"));
        } else {
            timeExpression = cb.function(
                    "MONTH",
                    Integer.class,
                    medicalReportJoin.get("createdDate")
            );
        }

        Expression<Double> revenueExpression = cb.sum(
                cb.prod(
                        cb.coalesce(reportDetailRoot.get("quantity")
                                .as(Double.class), 0.0),
                        cb.coalesce(medicineUnitJoin.get("unitPrice")
                                .as(Double.class), 0.0)
                )
        );

        query.multiselect(
                timeExpression,
                revenueExpression
        );
        query.groupBy(timeExpression);

        query.orderBy(cb.asc(timeExpression));

        List<Object[]> results = session.createQuery(query).getResultList();
        return results;
    }

}
