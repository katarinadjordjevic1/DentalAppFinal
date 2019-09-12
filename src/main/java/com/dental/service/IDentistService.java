/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dental.service;

import com.dental.entity.Dentist;
import com.dental.entity.Treatement;
import java.util.List;

/**
 *
 * @author Katarina Djordjevic
 */
public interface IDentistService {

    public Dentist findOne(Long id) throws Exception;

    public Long save(Dentist dentist) throws Exception;

    public void delete(Long id) throws Exception;

    public Dentist update(Long id, Dentist dentist) throws Exception;

    public List<Dentist> findAll() throws Exception;

    public Dentist findDentist(String username, String password) throws Exception;
}
