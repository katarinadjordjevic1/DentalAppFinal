/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dental.controllers;

import com.dental.entity.User;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Katarina Djordjevic
 */
public class AuthenticateController {

    public boolean authenticate(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return true;
        }
        return false;
    }
}
