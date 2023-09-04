package com.clinic.pojo;

import com.clinic.pojo.Medicine;
import com.clinic.pojo.ReportDetail;
import com.clinic.pojo.Unit;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-09-04T21:18:25")
@StaticMetamodel(MedicineUnit.class)
public class MedicineUnit_ { 

    public static volatile SingularAttribute<MedicineUnit, Integer> unitPrice;
    public static volatile SingularAttribute<MedicineUnit, Integer> quantity;
    public static volatile CollectionAttribute<MedicineUnit, ReportDetail> reportDetailCollection;
    public static volatile SingularAttribute<MedicineUnit, Medicine> medicineId;
    public static volatile SingularAttribute<MedicineUnit, Unit> unitId;
    public static volatile SingularAttribute<MedicineUnit, Integer> id;

}