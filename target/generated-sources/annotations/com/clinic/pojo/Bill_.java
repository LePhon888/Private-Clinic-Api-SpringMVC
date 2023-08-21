package com.clinic.pojo;

import com.clinic.pojo.MedicalReport;
import com.clinic.pojo.Nurse;
import com.clinic.pojo.Regulation;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-08-20T22:06:53")
@StaticMetamodel(Bill.class)
public class Bill_ { 

    public static volatile SingularAttribute<Bill, Regulation> regulationId;
    public static volatile SingularAttribute<Bill, Nurse> nurseId;
    public static volatile SingularAttribute<Bill, Integer> id;
    public static volatile CollectionAttribute<Bill, MedicalReport> medicalReportCollection;

}