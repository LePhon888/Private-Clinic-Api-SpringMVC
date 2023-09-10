package com.clinic.pojo;

import com.clinic.pojo.Category;
import com.clinic.pojo.MedicineUnit;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-09-10T21:31:32")
@StaticMetamodel(Medicine.class)
public class Medicine_ { 

    public static volatile CollectionAttribute<Medicine, MedicineUnit> medicineUnitCollection;
    public static volatile SingularAttribute<Medicine, String> name;
    public static volatile SingularAttribute<Medicine, Integer> id;
    public static volatile SingularAttribute<Medicine, Category> categoryId;

}