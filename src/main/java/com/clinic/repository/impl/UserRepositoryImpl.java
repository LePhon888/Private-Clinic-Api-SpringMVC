/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.repository.impl;

import com.clinic.pojo.User;
import com.clinic.pojo.Doctor;
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

/**
 *
 * @author admin
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository{

    @Autowired
    private LocalSessionFactoryBean factory;

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
        // Handle the case when no user is found with the given name
        return null; // or throw a custom exception, return a custom response, etc.
    }
}


    @Override
    public User getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User WHERE username=:un");
        q.setParameter("un", username);

        return (User) q.getSingleResult();    }

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
            // Handle the case when no user is found with the given name
            return null; // or throw a custom exception, return a custom response, etc.
        }
    }

    @Override
    public User createUser(User user) {
        Session session  = this.factory.getObject().getCurrentSession();
        try {
            session.save(user);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    
}
