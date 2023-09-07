package com.clinic.pojo;

import com.clinic.pojo.MedicalReport;
import com.clinic.pojo.ScheduleDetail;
import com.clinic.pojo.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-09-06T17:02:42")
@StaticMetamodel(Patient.class)
public class Patient_ { 

    public static volatile SingularAttribute<Patient, Integer> id;
    public static volatile SingularAttribute<Patient, User> userId;
    public static volatile CollectionAttribute<Patient, ScheduleDetail> scheduleDetailCollection;
    public static volatile CollectionAttribute<Patient, MedicalReport> medicalReportCollection;

}