package com.clinic.pojo;

import com.clinic.pojo.Bill;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-09-06T17:02:42")
@StaticMetamodel(Regulation.class)
public class Regulation_ { 

    public static volatile SingularAttribute<Regulation, Integer> numberOfPatients;
    public static volatile SingularAttribute<Regulation, Date> createdDate;
    public static volatile SingularAttribute<Regulation, Double> fee;
    public static volatile SingularAttribute<Regulation, Integer> id;
    public static volatile CollectionAttribute<Regulation, Bill> billCollection;

}