/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dental.service.impl;

import com.dental.dao.DentalServiceDAO;
import com.dental.entity.DentalService;
import com.dental.service.IDentalService;
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
public class DentalServiceImpl implements IDentalService {

    @Autowired
    private DentalServiceDAO dentalServiceDAO;

    @Override
    public List<DentalService> findAll() {
        System.out.println("Usao u poziv findAll dental service impl");
        return dentalServiceDAO.findAll();
    }

    @Override
    public DentalService findOne(Long id) throws Exception {
        DentalService dentalService = dentalServiceDAO.findOne(id);
        if (dentalService == null) {
            throw new Exception("Dental service was not found.");
        }
        return dentalService;
    }

}
