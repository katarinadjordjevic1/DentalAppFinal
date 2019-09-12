/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dental.service.impl;

import com.dental.dao.ToothDAO;
import com.dental.dao.impl.ToothDaoImpl;
import com.dental.entity.Tooth;
import com.dental.service.IToothService;
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
public class ToothServiceImpl implements IToothService {

    @Autowired
    private ToothDAO toothDAO;

    @Override
    public List<Tooth> findAll() {
        List<Tooth> list = toothDAO.findAll();
        return list;
    }

    @Override
    public Tooth findOne(Long id) throws Exception {
        return toothDAO.findOne(id);
    }

}
