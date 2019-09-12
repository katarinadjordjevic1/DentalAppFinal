/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dental.controllers;

import com.dental.entity.User;
import com.dental.helpers.Message;
import com.dental.helpers.Constants;
import com.dental.service.IUserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Katarina Djordjevic
 */
@Controller
public class LogInController extends AuthenticateController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLogin(HttpSession httpSession) {
        return authenticate(httpSession) ? new ModelAndView("index") : new ModelAndView("login");
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session) {

        if (authenticate(session)) {
            return new ModelAndView("index");
        }
        User user = userService.findUser(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("type", Message.SUCCESS);
            modelAndView.addObject("value", "Success log in, welcome " + username + "!");
            return modelAndView;
        }
        Message message = new Message(Message.ERROR, "Wrong username/password! Try again.");
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("type", message.getType());
        modelAndView.addObject("value", message.getValue());
        return modelAndView;
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session) {
        if (authenticate(session)) {
            session.removeAttribute("user");
            Message message = new Message(Message.INFO, "You are log out. Please log in again!");
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("type", message.getType());
            modelAndView.addObject("value", message.getValue());
            return modelAndView;
        }
        return new ModelAndView("login");
    }

}
