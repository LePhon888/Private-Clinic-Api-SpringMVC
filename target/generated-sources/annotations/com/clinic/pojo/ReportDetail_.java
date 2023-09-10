package com.clinic.pojo;

import com.clinic.pojo.MedicalReport;
import com.clinic.pojo.MedicineUnit;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-09-10T21:31:32")
@StaticMetamodel(ReportDetail.class)
public class ReportDetail_ { 

    public static volatile SingularAttribute<ReportDetail, String> usageInfo;
    public static volatile SingularAttribute<ReportDetail, Integer> quantity;
    public static volatile SingularAttribute<ReportDetail, MedicineUnit> medicineUnitId;
    public static volatile SingularAttribute<ReportDetail, MedicalReport> medicalreportId;
    public static volatile SingularAttribute<ReportDetail, Integer> id;

}