/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dental.service;

import com.dental.entity.Tooth;
import java.util.List;

/**
 *
 * @author Katarina Djordjevic
 */
public interface IToothService {

    public List<Tooth> findAll();
    
    public Tooth findOne(Long id) throws Exception;
}
