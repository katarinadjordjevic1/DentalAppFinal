/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dental.controllers;

import com.dental.entity.Dentist;
import com.dental.helpers.Message;
import com.dental.service.IDentistService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Katarina Djordjevic
 */
@Controller
@RequestMapping(value = "/dentist")
public class DentistController extends AuthenticateController {

    @Autowired
    IDentistService dentistService;

    public DentistController() {
        System.out.println("Usao u konstruktor");
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView getAllDentist(HttpSession session, Model model) {
        if (!authenticate(session)) {
            return new ModelAndView("login");
        }
        ModelAndView modelAndView = new ModelAndView("dentist/index");

        if (model.asMap().get("message") != null) {
            modelAndView.addObject("value", ((Message) model.asMap().get("message")).getValue());
            modelAndView.addObject("type", ((Message) model.asMap().get("message")).getType());
        }

        try {
            List<Dentist> dentists = dentistService.findAll();
            modelAndView.addObject("dentistList", dentists);

            if (dentists.isEmpty()) {
                modelAndView.addObject("message", new Message(Message.SUCCESS, "No avaliable dentist"));
            }
        } catch (Exception ex) {
            modelAndView.addObject("message", new Message(Message.ERROR, "Try again later to read dentist!"));
        }
        return modelAndView;
    }

    @RequestMapping(value = "/details/{dentistID}", method = RequestMethod.GET)
    public ModelAndView details(@PathVariable Long dentistID, HttpSession session) {
        if (!authenticate(session)) {
            return new ModelAndView("login");
        }
        ModelAndView modelAndView = new ModelAndView("dentist/details");
        try {
            Dentist dentist = dentistService.findOne(dentistID);
            modelAndView.addObject("dentist", dentist);
            modelAndView.addObject("type", Message.SUCCESS);
            modelAndView.addObject("value", "Dentist with ID " + dentistID + " was found.");
            return modelAndView;
        } catch (Exception ex) {
            modelAndView = new ModelAndView("dentist/index");
            modelAndView.addObject("type", Message.ERROR);
            modelAndView.addObject("value", ex.getMessage());
            return modelAndView;
        }
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public ModelAndView insert_get(HttpSession session) {
        if (!authenticate(session)) {
            return new ModelAndView("login");
        }
        ModelAndView mv = new ModelAndView("dentist/insert");
        mv.addObject("dentist", new Dentist());
        return mv;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert_post(@ModelAttribute("dentist") Dentist dentist, HttpSession session, final RedirectAttributes redirectAttributes) {
        if (!authenticate(session)) {
            return "redirect:/login";
        }
        try {
            Long save = dentistService.save(dentist);
            redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS, "Dentist was successfully saved!"));
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("message", new Message(Message.ERROR, "Dentist was not saved, try again later!"));
        }
        return "redirect:/dentist/index";

    }

    @RequestMapping(value = "/edit/{dentistID}", method = RequestMethod.GET)
    public ModelAndView edit_get(@PathVariable Long dentistID, HttpSession session) {
        if (!authenticate(session)) {
            return new ModelAndView("login");
        }
        try {
            ModelAndView mv = new ModelAndView("dentist/update");
            Dentist dentist = dentistService.findOne(dentistID);
            mv.addObject("dentist", dentist);
            mv.addObject("type", Message.SUCCESS);
            mv.addObject("value", "Dentist with ID " + dentist + " is found!");
            return mv;
        } catch (Exception ex) {
            return new ModelAndView("dentist/index", "message", new Message(Message.SUCCESS, "Dentist with ID " + dentistID + " is not found!"));
        }
    }

    @RequestMapping(value = "/edit/{dentistID}", method = RequestMethod.POST)
    public String edit_post(@PathVariable Long dentistID, @ModelAttribute("dentist") Dentist dentist, HttpSession session, final RedirectAttributes redirectAttributes) {
        if (!authenticate(session)) {
            return "redirect:login";
        }
        try {
            Dentist dentist1 = dentistService.update(dentistID, dentist);
            redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS, "Dentist was successfully updated!"));
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("message", new Message(Message.ERROR, "Dentist was not update, try again later!"));
        }
        return "redirect:/dentist/index";
    }

    @RequestMapping(value = "/delete/{dentistID}", method = RequestMethod.GET)
    public ModelAndView delete_get(@PathVariable Long dentistID, HttpSession session) {
        if (!authenticate(session)) {
            return new ModelAndView("login");
        }
        try {
            Dentist dentist = dentistService.findOne(dentistID);
            ModelAndView mv = new ModelAndView("dentist/delete");
            mv.addObject("message", new Message(Message.SUCCESS, "Dentist with ID " + dentist + " is found!"));
            mv.addObject("dentist", dentist);
            return mv;
        } catch (Exception ex) {
            return new ModelAndView("dentist/index", "message", new Message(Message.ERROR, "Dentist was not found, try again later!"));
        }
    }

    @RequestMapping(value = "/delete/{dentistID}", method = RequestMethod.POST)
    public String delete_post(@PathVariable Long dentistID, HttpSession session, final RedirectAttributes redirectAttributes) {
        if (!authenticate(session)) {
            return "redirect:/login";
        }
        try {
            dentistService.delete(dentistID);
            redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS, "Dentist was successfully deleted!"));
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("message", new Message(Message.ERROR, "Dentist was not delete, try again later!"));
        }
        return "redirect:/dentist/index";
    }
}
