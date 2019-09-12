/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dental.service;

import com.dental.entity.Patient;
import com.dental.entity.Treatement;
import java.util.List;

/**
 *
 * @author Katarina Djordjevic
 */
public interface IPatientService {

    public Patient findOne(Long id) throws Exception;

    public Long save(Patient patient) throws Exception;

    public void delete(Long id) throws Exception;

    public Patient update(Long id, Patient patient) throws Exception;

    public List<Patient> findAll() throws Exception;

    public List<Treatement> findAllTreatements(Long id) throws Exception;


}
