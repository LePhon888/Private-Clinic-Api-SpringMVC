package com.clinic.pojo;

import com.clinic.pojo.MedicineUnit;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-09-02T21:40:01")
@StaticMetamodel(Unit.class)
public class Unit_ { 

    public static volatile CollectionAttribute<Unit, MedicineUnit> medicineUnitCollection;
    public static volatile SingularAttribute<Unit, String> name;
    public static volatile SingularAttribute<Unit, Integer> id;

}