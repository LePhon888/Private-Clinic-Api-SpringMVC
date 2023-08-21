package com.clinic.pojo;

import com.clinic.pojo.MedicalReport;
import com.clinic.pojo.Medicine;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-08-20T22:06:53")
@StaticMetamodel(ReportDetail.class)
public class ReportDetail_ { 

    public static volatile SingularAttribute<ReportDetail, String> usageInfo;
    public static volatile SingularAttribute<ReportDetail, Integer> quantity;
    public static volatile SingularAttribute<ReportDetail, Medicine> medicineId;
    public static volatile SingularAttribute<ReportDetail, MedicalReport> medicalreportId;
    public static volatile SingularAttribute<ReportDetail, Integer> id;

}