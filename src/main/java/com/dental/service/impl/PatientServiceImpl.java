/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dental.service.impl;

import com.dental.dao.PatientDAO;
import com.dental.entity.Patient;
import com.dental.entity.Treatement;
import com.dental.service.IPatientService;
import com.dental.service.ITreatementService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Katarina Djordjevic
 */
@Component
@Transactional
public class PatientServiceImpl implements IPatientService {

    @Autowired
    private PatientDAO patientDAO;
    @Autowired
    private ITreatementService treatementService;

    @Override
    public Patient findOne(Long id) throws Exception {
        System.out.println("Pozvana metoda findOne!!!!");
        Patient patient = patientDAO.findOne(id);

        if (patient == null) {
            System.out.println("Patient je null");
            throw new Exception("Patient was not found with ID " + id);
        }
        patient.setTreatementList(treatementService.findAllTreatementsForPatient(id));
        return patient;
    }

    @Override
    public Long save(Patient patient) throws Exception {
        
        Patient createdPatient = patientDAO.create(patient);
        if (createdPatient == null) {
            throw new Exception("Patient can't be created");
        }
        return createdPatient.getPatientID();
    }

    @Override
    public void delete(Long id) throws Exception {
        if (id < 1 || findOne(id) == null) {
            throw new Exception("Patient with ID " + id + " does not exist.");
        }
        patientDAO.delete(id);
    }

    @Override
    public Patient update(Long id, Patient patient) throws Exception {
        if (id < 1 || findOne(id) == null) {
            throw new Exception("Patient with ID " + id + " does not exist.");
        }
        patient.setPatientID(id);
        java.sql.Date datumSQL = new java.sql.Date(patient.getDateofbirth().getTime());
        patient.setDateofbirth(datumSQL);

        return patientDAO.update(patient);
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> patients = patientDAO.findAll();
        return patients;
    }

    @Override
    public List<Treatement> findAllTreatements(Long id) throws Exception {
        Patient patient = patientDAO.findOne(id);
        List<Treatement> treatements = patient.getTreatementList();
        return treatements;
    }


}
