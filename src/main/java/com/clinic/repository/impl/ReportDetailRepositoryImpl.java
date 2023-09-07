/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.repository.impl;

import com.clinic.pojo.ReportDetail;
import com.clinic.repository.ReportDetailRepository;
import java.util.List;
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
 * @author hp
 */
@Repository
@Transactional
public class ReportDetailRepositoryImpl implements ReportDetailRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<ReportDetail> getReportDetailByReport(Map<String, Object> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<ReportDetail> q = b.createQuery(ReportDetail.class);
        Root<ReportDetail> root = q.from(ReportDetail.class);
        q.select(root);
        if (params != null && !params.isEmpty()) {
            int reportId =   Integer.parseInt(params.get("id").toString());
              q.where(b.equal(root.get("medicalreportId").get("id"), reportId));
        }
        Query<ReportDetail> query = session.createQuery(q);
        return query.getResultList();
    }

}

