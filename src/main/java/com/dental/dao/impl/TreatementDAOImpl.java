/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dental.dao.impl;

import com.dental.dao.TreatementDAO;
import com.dental.entity.Dentist;
import com.dental.entity.Treatement;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Component;

/**
 *
 * @author Katarina Djordjevic
 */
@Component
public class TreatementDAOImpl extends GenericDaoImpl<Treatement> implements TreatementDAO {

    @Override
    public List<Treatement> findAllTreatementForPatient(Long patientID) {
        Query query = entityManager.
                createQuery("SELECT t FROM Treatement t where t.patient.patientID=" + patientID);

        List<Treatement> treatements = query.getResultList();
        return treatements;
    }
}
