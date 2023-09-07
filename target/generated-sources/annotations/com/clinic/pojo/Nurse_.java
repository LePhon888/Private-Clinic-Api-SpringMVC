package com.clinic.pojo;

import com.clinic.pojo.Bill;
import com.clinic.pojo.NurseShift;
import com.clinic.pojo.ScheduleDetail;
import com.clinic.pojo.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-09-06T17:02:42")
@StaticMetamodel(Nurse.class)
public class Nurse_ { 

    public static volatile CollectionAttribute<Nurse, NurseShift> nurseShiftCollection;
    public static volatile SingularAttribute<Nurse, Integer> id;
    public static volatile CollectionAttribute<Nurse, Bill> billCollection;
    public static volatile SingularAttribute<Nurse, User> userId;
    public static volatile CollectionAttribute<Nurse, ScheduleDetail> scheduleDetailCollection;

}