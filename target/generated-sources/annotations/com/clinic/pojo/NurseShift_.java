package com.clinic.pojo;

import com.clinic.pojo.Nurse;
import com.clinic.pojo.Shift;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-08-20T22:06:53")
@StaticMetamodel(NurseShift.class)
public class NurseShift_ { 

    public static volatile SingularAttribute<NurseShift, Date> date;
    public static volatile SingularAttribute<NurseShift, Shift> shiftId;
    public static volatile SingularAttribute<NurseShift, Nurse> nurseId;
    public static volatile SingularAttribute<NurseShift, Integer> id;

}