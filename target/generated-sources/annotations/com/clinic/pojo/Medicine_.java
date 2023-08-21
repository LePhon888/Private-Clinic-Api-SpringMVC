package com.clinic.pojo;

import com.clinic.pojo.Category;
import com.clinic.pojo.ReportDetail;
import com.clinic.pojo.UnitMedicine;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-08-20T22:06:53")
@StaticMetamodel(Medicine.class)
public class Medicine_ { 

    public static volatile SingularAttribute<Medicine, Integer> unitPrice;
    public static volatile CollectionAttribute<Medicine, ReportDetail> reportDetailCollection;
    public static volatile SingularAttribute<Medicine, String> name;
    public static volatile CollectionAttribute<Medicine, UnitMedicine> unitMedicineCollection;
    public static volatile SingularAttribute<Medicine, Integer> id;
    public static volatile SingularAttribute<Medicine, Category> categoryId;

}