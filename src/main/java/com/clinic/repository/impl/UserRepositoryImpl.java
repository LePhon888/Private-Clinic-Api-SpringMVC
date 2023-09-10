/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.repository.impl;

import com.clinic.pojo.User;
import com.clinic.pojo.Doctor;
import com.clinic.pojo.Patient;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.clinic.repository.UserRepository;
import java.util.Map;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author admin
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Override
    public User getUserByName(String name) {
        Session s = this.factory.getObject().getCurrentSession();

        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<User> q = b.createQuery(User.class);
        Root<User> root = q.from(User.class);
        q.select(root);

        try {
            Predicate predicate = b.equal(root.get("name"), name.trim());
            q.where(predicate);

            Query<User> query = s.createQuery(q);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;         }
    }

    @Override
    public User getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();

        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<User> q = b.createQuery(User.class);
        Root<User> root = q.from(User.class);
        q.select(root);

        try {
            Predicate predicate = b.equal(root.get("username"), username.trim());
            q.where(predicate);

            Query<User> query = s.createQuery(q);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; 
        }
    }

    @Override
    public User getUserById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<User> q = b.createQuery(User.class);
        Root<User> root = q.from(User.class);
        q.select(root);

        try {
            Predicate predicate = b.equal(root.get("id"), id);
            q.where(predicate);

            Query<User> query = s.createQuery(q);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; 
        }
    }

    @Override
    public User createUser(User user) {
        Session session = this.factory.getObject().getCurrentSession();
        Patient patient = new Patient();
        try {
            patient.setUserId(user);
            session.save(user);
            session.save(patient);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean authUser(String username, String password) {
        User u = this.getUserByUsername(username);
        
        return this.passEncoder.matches(password, u.getPassword());

    }
    

    @Override
    public User getUserByEmail(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();

        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<User> q = b.createQuery(User.class);
        Root<User> root = q.from(User.class);
        q.select(root);

        try {
            String email = params.get("email");
            if (email != null && !email.isEmpty()) {
                Predicate predicate = b.equal(root.get("email"), email.trim());
                q.where(predicate);
                Query<User> query = s.createQuery(q);
                return query.getSingleResult();
            }
            return null;
        } catch (NoResultException e) {
            return null; 
        }    
    }

    @Override
    public User getUserByPhoneNumber(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();

        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<User> q = b.createQuery(User.class);
        Root<User> root = q.from(User.class);
        q.select(root);

        try {
            String phoneNumber = params.get("phoneNumber");
            if (phoneNumber != null && !phoneNumber.isEmpty()) {
                Predicate predicate = b.equal(root.get("phoneNumber"), phoneNumber.trim());
                q.where(predicate);
                Query<User> query = s.createQuery(q);
                return query.getSingleResult();
            }
            return null;
        } catch (NoResultException e) {
            return null; 
        } 
    }

}
