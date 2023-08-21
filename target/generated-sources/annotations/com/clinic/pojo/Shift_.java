package com.clinic.pojo;

import com.clinic.pojo.DoctorShift;
import com.clinic.pojo.NurseShift;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-08-21T22:44:20")
@StaticMetamodel(Shift.class)
public class Shift_ { 

    public static volatile CollectionAttribute<Shift, DoctorShift> doctorShiftCollection;
    public static volatile CollectionAttribute<Shift, NurseShift> nurseShiftCollection;
    public static volatile SingularAttribute<Shift, String> startTime;
    public static volatile SingularAttribute<Shift, Integer> id;
    public static volatile SingularAttribute<Shift, String> endTime;

}