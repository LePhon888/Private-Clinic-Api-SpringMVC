package com.clinic.pojo;

import com.clinic.pojo.Doctor;
import com.clinic.pojo.Shift;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-09-06T17:02:42")
@StaticMetamodel(DoctorShift.class)
public class DoctorShift_ { 

    public static volatile SingularAttribute<DoctorShift, Date> date;
    public static volatile SingularAttribute<DoctorShift, Shift> shiftId;
    public static volatile SingularAttribute<DoctorShift, Doctor> doctorId;
    public static volatile SingularAttribute<DoctorShift, Integer> id;

}