/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.repository.impl;

import com.clinic.pojo.Bill;
import com.clinic.pojo.MedicalReport;
import com.clinic.pojo.MedicineUnit;
import com.clinic.pojo.Regulation;
import com.clinic.pojo.ReportDetail;
import com.clinic.repository.BillRepository;
import com.clinic.repository.MedicalReportRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import org.hibernate.Session;
import org.mvel2.optimizers.dynamic.DynamicOptimizer;
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
public class BillRepositoryImpl implements BillRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private MedicalReportRepository medicalReportRepo;

    @Override
    public List<Object[]> getMedicineBillByMedicalReportId(Map<String, String> params) {

        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<ReportDetail> reportDetailRoot = query.from(ReportDetail.class);

        Join<ReportDetail, MedicineUnit> medicineUnitJoin
                = reportDetailRoot.join("medicineUnitId");

        List<Predicate> predicates = new ArrayList<>();
        List<Integer> idList = new ArrayList<>();
        String idString = params.get("medicalReportId");
        if (idString != null) {
            String[] idStrings = idString.split(",");
            for (String id : idStrings) {
                idList.add(Integer.parseInt(id));
            }
            Predicate predicate = reportDetailRoot
                    .get("medicalreportId")
                    .get("id")
                    .in(idList);
            predicates.add(predicate);
        }

        Expression<Double> totalUnitPriceExpression = cb.sum(
                cb.prod(
                        cb.coalesce(reportDetailRoot.get("quantity").as(Double.class), 0.0),
                        cb.coalesce(medicineUnitJoin.get("unitPrice").as(Double.class), 0.0)
                )
        );

        query.multiselect(reportDetailRoot
                .get("medicalreportId")
                .get("id"),
                totalUnitPriceExpression // Set total unit price to zero if null
        );

        query.where(predicates.toArray(new Predicate[0]));

        query.groupBy(
                reportDetailRoot.get("medicalreportId").get("id")
        );

        List<Object[]> resultList = session.createQuery(query).getResultList();

        return resultList;
    }

    @Override
    public List<Object[]> getBillByMedicalReportId(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<MedicalReport> medicalReportRoot = query.from(MedicalReport.class);

        Expression<Double> feeCoalesceExpression
                = medicalReportRoot
                        .get("billId")
                        .get("regulationId")
                        .get("fee").as(Double.class);

        List<Predicate> predicates = new ArrayList<>();
        List<Integer> idList = new ArrayList<>();
        String idString = params.get("medicalReportId");
        if (idString != null) {
            String[] idStrings = idString.split(",");
            for (String id : idStrings) {
                idList.add(Integer.parseInt(id));
            }
            Predicate predicate = medicalReportRoot
                    .get("id")
                    .in(idList);
            predicates.add(predicate);
        }

        query.multiselect(
                medicalReportRoot
                        .get("id"),
                feeCoalesceExpression
        );

        query.where(predicates.toArray(new Predicate[0]));

        List<Object[]> resultList = session.createQuery(query).getResultList();

        return resultList;
    }

}
