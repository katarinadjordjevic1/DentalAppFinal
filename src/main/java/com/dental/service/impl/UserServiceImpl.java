/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dental.service.impl;

import com.dental.dao.DentistDao;
import com.dental.entity.Dentist;
import com.dental.entity.User;
import com.dental.service.IUserService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Katarina Djordjevic
 */
@Component
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private DentistDao dentistDao;

    @Override
    public User findUser(String username, String password) {
        Dentist dentist = dentistDao.logIn(username, password);
        if (dentist != null) {
            User user = new User();
            System.out.println(dentist.getUsername());
            user.setUsername(dentist.getUsername());
            user.setPassword(dentist.getPassword());
            user.setRole(dentist.getRole());
            return user;
        }
        return null;

    }
}
