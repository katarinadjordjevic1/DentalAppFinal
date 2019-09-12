/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dental.dao.impl;

import com.dental.dao.GenericDAO;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Katarina Djordjevic
 * @param <T>
 */
public class GenericDaoImpl<T> implements GenericDAO<T> {

    protected EntityManager entityManager;

    private final Class<T> type;

    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_DentalApp_war_1.0-SNAPSHOTPU");
        entityManager = emf.createEntityManager();
    }

    @Override
    public T findOne(long id) {
        return entityManager.find(type, id);
    }

    @Override
    public List<T> findAll() {
        System.out.println(type.getSimpleName());
        System.out.println("Entity manager: " + entityManager);
        return entityManager.createNamedQuery(type.getSimpleName() + ".findAll", type).getResultList();
    }

    @Override
    public T create(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit(); 
        return entity;   
    }

    @Override
    public T update(T entity) {
        entityManager.getTransaction().begin();
        entity = entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public void delete(long id) {
        T entity = findOne(id);
        if (entity != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        } else {
            System.out.println("Greska pri brisanju!"); 
        
    }
}
}
