/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dental.service;

import com.dental.entity.DentalService;
import com.dental.entity.Intervention;
import com.dental.entity.Tooth;
import com.dental.entity.Treatement;
import com.dental.entity.User;
import java.util.List;

/**
 *
 * @author Katarina Djordjevic
 */
public interface ITreatementService {

    public Treatement findOne(Long id) throws Exception;

    public List<Tooth> findAllTooth();

    public List<DentalService> findAllService();

    public List<Treatement> findAll() throws Exception;
    
    public List<Treatement> findAllTreatementsForPatient(Long patientID);

    public Long save(Treatement treatement, User user) throws Exception;

}
