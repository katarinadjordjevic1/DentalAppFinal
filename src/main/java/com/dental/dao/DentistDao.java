/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dental.dao;

import com.dental.entity.Dentist;


/**
 *
 * @author Katarina Djordjevic
 */
public interface DentistDao extends GenericDAO<Dentist> {

    public Dentist logIn(String username, String password);

}
