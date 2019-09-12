/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dental.dao;

import com.dental.entity.Treatement;
import java.util.List;

/**
 *
 * @author Katarina Djordjevic
 */
public interface TreatementDAO extends GenericDAO<Treatement> {

    public List<Treatement> findAllTreatementForPatient(Long patientID);
}
