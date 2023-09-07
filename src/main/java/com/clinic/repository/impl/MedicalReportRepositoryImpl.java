/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.repository.impl;

import com.clinic.pojo.MedicalReport;
import com.clinic.pojo.MedicineUnit;
import com.clinic.pojo.ReportDetail;
import com.clinic.repository.DoctorRepository;
import com.clinic.repository.MedicalReportRepository;
import com.clinic.repository.MedicineUnitRepository;
import com.clinic.repository.PatientRepository;
import freemarker.template.SimpleDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;
    
    @Autowired
    private MedicineUnitRepository medicineUnitRepository;

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

             String fromDate = params.getOrDefault(("fromDate"), "").toString();
            if (fromDate != null && !fromDate.isEmpty()) {
                try {
                    Date fromDateParams = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
                    // Set time component to start of day (00:00:00)
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(fromDateParams);
                    cal.set(Calendar.HOUR_OF_DAY, 0);
                    cal.set(Calendar.MINUTE, 0);
                    cal.set(Calendar.SECOND, 0);
                    fromDateParams = cal.getTime();
                    predicates.add(b.greaterThanOrEqualTo(root.get("createdDate"), fromDateParams));
                } catch (ParseException ex) {
                    Logger.getLogger(MedicalReportRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            String toDate = params.getOrDefault(("toDate"), "").toString();
            if (toDate != null && !toDate.isEmpty()) {
                try {
                    Date toDateParams = new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
                    // Set time component to end of day (23:59:59)
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(toDateParams);
                    cal.set(Calendar.HOUR_OF_DAY, 23);
                    cal.set(Calendar.MINUTE, 59);
                    cal.set(Calendar.SECOND, 59);
                    toDateParams = cal.getTime();
                    predicates.add(b.lessThanOrEqualTo(root.get("createdDate"), toDateParams));
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
    
     @Override
    public boolean create(Map<String, Object> object) {
        Session session = factory.getObject().getCurrentSession();
        boolean result = false;
        try {
            if (object != null) {

                Map<String, Object> medicalReportMap = (Map<String, Object>) object.get("medicalReport");
                List<Map<String, Object>> reportDetailList = (List<Map<String, Object>>) object.get("reportDetail");
                String symptom = medicalReportMap.get("symptom").toString();
                String diagnose = medicalReportMap.get("diagnose").toString();
                String createdDate = medicalReportMap.get("createdDate").toString();

                Map<String, Object> patientMap = (Map<String, Object>) medicalReportMap.get("patientId");
                int patientId = (int) patientMap.get("id");

                Map<String, Object> doctorMap = (Map<String, Object>) medicalReportMap.get("doctorId");
                int doctorId = (int) doctorMap.getOrDefault("id", 1);

                MedicalReport medicalReport = new MedicalReport();
                medicalReport.setSyntomp(symptom);
                medicalReport.setDiagnose(diagnose);
                medicalReport.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd").parse(createdDate));
                medicalReport.setPatientId(patientRepository.getById(patientId));
                medicalReport.setDoctorId(doctorRepository.getDoctorByUserId(doctorId));

                session.save(medicalReport);

                if (reportDetailList != null && !reportDetailList.isEmpty()) {
                    // Iterate through the reportDetailList
                    for (Map<String, Object> reportDetailMap : reportDetailList) {
                        Map<String, Object> medicineUnitMap = (Map<String, Object>) reportDetailMap.get("medicineUnitId");

                        if (medicineUnitMap != null) {
                            Map<String, Object> medicineMap = (Map<String, Object>) medicineUnitMap.get("medicineId");
                            Map<String, Object> unitMap = (Map<String, Object>) medicineUnitMap.get("unitId");

                            if (medicineMap != null && unitMap != null) {
                                int medicineId = (int) medicineMap.get("id");
                                int unitId = (int) unitMap.get("id");
                                
   
                                String quantity = reportDetailMap.get("quantity").toString();
                                String usageInfo = reportDetailMap.get("usageInfo").toString();
                                
                                MedicineUnit medicineUnit = medicineUnitRepository.getOrCreateByMedicineUnit(medicineId, unitId);
                                medicineUnit.setQuantity(medicineUnit.getQuantity() - Integer.valueOf(quantity));
                                medicineUnitRepository.update(medicineUnit);
                                
                                ReportDetail reportDetail = new ReportDetail();
                                reportDetail.setQuantity(Integer.parseInt(quantity));
                                reportDetail.setUsageInfo(usageInfo);
                                reportDetail.setMedicalreportId(medicalReport);
                                reportDetail.setMedicineUnitId(medicineUnit);

                                session.save(reportDetail);
                            }
                        }
                    }
                }
                result = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return result;
    }

}
