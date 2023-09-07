package com.clinic.pojo;

import com.clinic.pojo.Doctor;
import com.clinic.pojo.Hour;
import com.clinic.pojo.Nurse;
import com.clinic.pojo.Patient;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-09-06T17:02:42")
@StaticMetamodel(ScheduleDetail.class)
public class ScheduleDetail_ { 

    public static volatile SingularAttribute<ScheduleDetail, Date> date;
    public static volatile SingularAttribute<ScheduleDetail, String> reason;
    public static volatile SingularAttribute<ScheduleDetail, Patient> registerPatient;
    public static volatile SingularAttribute<ScheduleDetail, Short> isCancel;
    public static volatile SingularAttribute<ScheduleDetail, Short> isConfirm;
    public static volatile SingularAttribute<ScheduleDetail, Hour> hourId;
    public static volatile SingularAttribute<ScheduleDetail, Doctor> doctorId;
    public static volatile SingularAttribute<ScheduleDetail, Patient> patientId;
    public static volatile SingularAttribute<ScheduleDetail, Nurse> nurseId;
    public static volatile SingularAttribute<ScheduleDetail, Integer> id;

}