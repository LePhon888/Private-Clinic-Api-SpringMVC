package com.clinic.pojo;

import com.clinic.pojo.Department;
import com.clinic.pojo.DoctorShift;
import com.clinic.pojo.MedicalReport;
import com.clinic.pojo.ScheduleDetail;
import com.clinic.pojo.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-08-21T22:44:20")
@StaticMetamodel(Doctor.class)
public class Doctor_ { 

    public static volatile CollectionAttribute<Doctor, DoctorShift> doctorShiftCollection;
    public static volatile SingularAttribute<Doctor, Department> departmentId;
    public static volatile SingularAttribute<Doctor, Integer> id;
    public static volatile SingularAttribute<Doctor, User> userId;
    public static volatile CollectionAttribute<Doctor, ScheduleDetail> scheduleDetailCollection;
    public static volatile CollectionAttribute<Doctor, MedicalReport> medicalReportCollection;

}