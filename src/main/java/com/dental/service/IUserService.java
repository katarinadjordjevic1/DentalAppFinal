/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dental.service;

import com.dental.entity.User;

/**
 *
 * @author Katarina Djordjevic
 */
public interface IUserService {

    public User findUser(String username, String password);
}
