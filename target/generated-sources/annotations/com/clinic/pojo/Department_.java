package com.clinic.pojo;

import com.clinic.pojo.Doctor;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-08-21T22:44:20")
@StaticMetamodel(Department.class)
public class Department_ { 

    public static volatile SingularAttribute<Department, String> name;
    public static volatile CollectionAttribute<Department, Doctor> doctorCollection;
    public static volatile SingularAttribute<Department, Integer> id;
    public static volatile SingularAttribute<Department, String> detail;

}