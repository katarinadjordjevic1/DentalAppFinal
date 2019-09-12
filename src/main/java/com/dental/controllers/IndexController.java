/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dental.controllers;

import com.dental.entity.User;
import com.dental.helpers.Constants;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Katarina Djordjevic
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            return "index";
        }
        return "login";
    }

}
