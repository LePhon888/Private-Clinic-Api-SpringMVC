package com.clinic.pojo;

import com.clinic.pojo.Admin;
import com.clinic.pojo.Doctor;
import com.clinic.pojo.Nurse;
import com.clinic.pojo.Patient;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-08-20T22:06:53")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> birthday;
    public static volatile SingularAttribute<User, String> image;
    public static volatile SingularAttribute<User, String> address;
    public static volatile SingularAttribute<User, String> gender;
    public static volatile CollectionAttribute<User, Patient> patientCollection;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> phoneNumber;
    public static volatile CollectionAttribute<User, Admin> adminCollection;
    public static volatile CollectionAttribute<User, Nurse> nurseCollection;
    public static volatile SingularAttribute<User, String> name;
    public static volatile CollectionAttribute<User, Doctor> doctorCollection;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> userRole;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> username;

}