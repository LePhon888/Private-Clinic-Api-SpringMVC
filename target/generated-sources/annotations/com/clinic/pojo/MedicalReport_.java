package com.clinic.pojo;

import com.clinic.pojo.Bill;
import com.clinic.pojo.Doctor;
import com.clinic.pojo.Patient;
import com.clinic.pojo.ReportDetail;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-09-04T21:18:25")
@StaticMetamodel(MedicalReport.class)
public class MedicalReport_ { 

    public static volatile SingularAttribute<MedicalReport, Short> isPaid;
    public static volatile SingularAttribute<MedicalReport, Date> createdDate;
    public static volatile CollectionAttribute<MedicalReport, ReportDetail> reportDetailCollection;
    public static volatile SingularAttribute<MedicalReport, Doctor> doctorId;
    public static volatile SingularAttribute<MedicalReport, Patient> patientId;
    public static volatile SingularAttribute<MedicalReport, String> diagnose;
    public static volatile SingularAttribute<MedicalReport, Bill> billId;
    public static volatile SingularAttribute<MedicalReport, Integer> id;
    public static volatile SingularAttribute<MedicalReport, String> syntomp;

}