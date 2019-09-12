/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dental.dao;

import java.util.List;

/**
 *
 * @author Katarina Djordjevic
 * @param <T>
 */
public interface GenericDAO<T> {

    public T findOne(long id);

    public List<T> findAll();

    public T create(T entity);

    public T update(T entity);

    public void delete(long id);
}
