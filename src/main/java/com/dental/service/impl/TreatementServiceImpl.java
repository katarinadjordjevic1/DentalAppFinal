/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dental.service.impl;

import com.dental.dao.TreatementDAO;
import com.dental.entity.DentalService;
import com.dental.entity.Dentist;
import com.dental.entity.Intervention;
import com.dental.entity.Tooth;
import com.dental.entity.Treatement;
import com.dental.entity.User;
import com.dental.service.IDentalService;
import com.dental.service.IDentistService;
import com.dental.service.IToothService;
import com.dental.service.ITreatementService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Katarina Djordjevic
 */

@Component
@Transactional
public class TreatementServiceImpl implements ITreatementService {

    @Autowired
    private TreatementDAO treatementDAO;
    @Autowired
    private IToothService toothService;
    @Autowired
    private IDentalService dentalService;
    @Autowired
    private IDentistService dentistService;

    @Override
    public Treatement findOne(Long id) throws Exception {
        Treatement treatement = treatementDAO.findOne(id);

        if (treatement == null) {
            System.out.println("Treatement je null");
            throw new Exception("Treatement was not found with ID " + id);
        }
        return treatement;
    }

    @Override
    public List<Treatement> findAll() throws Exception {
        List<Treatement> list = treatementDAO.findAll();
        return list;
    }

    @Override
    public List<Tooth> findAllTooth() {
        System.out.println("Usao u poziv");
        return toothService.findAll();
    }

    @Override
    public List<DentalService> findAllService() {
        System.out.println("Usao u poziv");
        return dentalService.findAll();
    }

    @Override
    public Long save(Treatement treatement, User user) throws Exception {
        try {
            Dentist dentist = dentistService.findDentist(user.getUsername(), user.getPassword());
            treatement.setDentist(dentist);
            double cost = 0;

            for (Intervention intervention : treatement.getInterventionList()) {
                intervention.setTreatementFK(treatement);
                cost += intervention.getDentalService().getCost() * (1 + intervention.getDentalService().getVat());
            }
            treatement.setCost(cost);
            Treatement createdTreatement = treatementDAO.create(treatement);
            return createdTreatement.getTreatementID();
        } catch (Exception ex) {
            throw new Exception("Treatement was not saved, try again later.");
        }
    }

    @Override
    public List<Treatement> findAllTreatementsForPatient(Long patientID) {
        return treatementDAO.findAllTreatementForPatient(patientID);
    }
}
