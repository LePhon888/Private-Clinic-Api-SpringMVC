package com.clinic.pojo;

import com.clinic.pojo.ScheduleDetail;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-09-04T21:18:25")
@StaticMetamodel(Hour.class)
public class Hour_ { 

    public static volatile SingularAttribute<Hour, String> hour;
    public static volatile SingularAttribute<Hour, Integer> id;
    public static volatile CollectionAttribute<Hour, ScheduleDetail> scheduleDetailCollection;

}