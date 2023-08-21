package com.clinic.repository.impl;

import com.clinic.pojo.Department;
import com.clinic.pojo.Doctor;
import com.clinic.pojo.ScheduleDetail;
import com.clinic.pojo.Hour;
import com.clinic.pojo.User;
import com.clinic.pojo.Patient;
import com.clinic.repository.DepartmentRepository;
import com.clinic.repository.DoctorRepository;
import com.clinic.repository.HourRepository;
import com.clinic.repository.ScheduleDetailRepository;
import com.clinic.repository.UserRepository;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
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

@Repository
@Transactional
public class ScheduleDetailRepositoryImpl implements ScheduleDetailRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Autowired
    private SimpleDateFormat dateFormat;
    
    @Autowired
    private DoctorRepository doctorRepo;
    
    @Autowired
    private HourRepository hourRepo;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<ScheduleDetail> getScheduleDetailByDate(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<ScheduleDetail> q = b.createQuery(ScheduleDetail.class);
        Root<ScheduleDetail> root = q.from(ScheduleDetail.class);
        q.select(root);

        if (params != null) {
            String date = params.get("date");
            try {
                Predicate predicate = b.equal(root.get("date"), Integer.parseInt(date));
                q.where(predicate);
            } catch (NumberFormatException e) {
                // Handle the invalid departmentId value here if needed
            }
        }
        Query<ScheduleDetail> query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public Boolean createScheduleDetail(Map<String, Object> schedule) {
        Session session = this.factory.getObject().getCurrentSession();

        try {
            ScheduleDetail scheduleDetail = new ScheduleDetail();

            Map<String, Object> userDoctorData = (Map<String, Object>) schedule.get("doctorId");
            // ... rest of the code for creating Doctor, Hour, User, Patient, and ScheduleDetail ...
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ScheduleDetail getScheduleDetailById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<ScheduleDetail> q = b.createQuery(ScheduleDetail.class);
        Root<ScheduleDetail> root = q.from(ScheduleDetail.class);
        q.select(root);

        try {
            Predicate predicate = b.equal(root.get("id"), id);
            q.where(predicate);
        } catch (NumberFormatException e) {
            // Handle the invalid departmentId value here if needed
        }
        Query<ScheduleDetail> query = session.createQuery(q);
        return query.getSingleResult();
    }

    @Override
    public Boolean updateIsConfirm(int scheduleDetailId, Map<String, Short> isConfirm) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            Short isConfirmValue = isConfirm.get("isConfirm");
            ScheduleDetail scheduleDetail = getScheduleDetailById(scheduleDetailId); // Fixed variable name
            if (scheduleDetail != null) {
                scheduleDetail.setIsConfirm(isConfirmValue);
                session.update(scheduleDetail);
                return true;
            } else {
                return false; // ScheduleDetail not found
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
