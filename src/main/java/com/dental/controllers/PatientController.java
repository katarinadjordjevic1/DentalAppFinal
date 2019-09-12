/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dental.controllers;

import com.dental.entity.Patient;
import com.dental.entity.Treatement;
import com.dental.helpers.Message;
import com.dental.service.IPatientService;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
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
@RequestMapping(value = "/patient")
public class PatientController extends AuthenticateController {

    @Autowired
    IPatientService patientService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView getAllPatient(HttpSession session, Model model) {
        if (!authenticate(session)) {
            return new ModelAndView("login");
        }
        ModelAndView modelAndView = new ModelAndView("patient/index");
        if (model.asMap().get("message") != null) {
            modelAndView.addObject("value", ((Message) model.asMap().get("message")).getValue());
            modelAndView.addObject("type", ((Message) model.asMap().get("message")).getType());
        }
        try {
            List<Patient> patients = patientService.findAll();
            modelAndView.addObject("patientList", patients);
            for (Patient patient : patients) {
                System.out.println(patient.getFirstname());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            modelAndView.addObject("patientList", new LinkedList<Patient>());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/details/{patientID}", method = RequestMethod.GET)
    public ModelAndView details(@PathVariable Long patientID, HttpSession session) {
        if (!authenticate(session)) {
            return new ModelAndView("login");
        }
        ModelAndView modelAndView = new ModelAndView("patient/details");
        try {
            Patient patient = patientService.findOne(patientID);
            for (Treatement treatement : patient.getTreatementList()) {
                System.out.println("Cena: " + treatement.getCost());
            }
            modelAndView.addObject("patient", patient);
            modelAndView.addObject("type", Message.SUCCESS);
            modelAndView.addObject("value", "Patient with ID " + patientID + " was found.");
        } catch (Exception ex) {
            modelAndView = new ModelAndView("patient/index");
            modelAndView.addObject("patient", null);
            modelAndView.addObject("type", Message.ERROR);
            modelAndView.addObject("value", ex.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public ModelAndView insert_get(HttpSession session) {
        if (!authenticate(session)) {
            return new ModelAndView("login");
        }
        ModelAndView mv = new ModelAndView("patient/insert");
        mv.addObject("patient", new Patient());
        return mv;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert_post(@ModelAttribute("patient") Patient patient, HttpSession session, final RedirectAttributes redirectAttributes) {
        if (!authenticate(session)) {
            return "redirect:login";
        }
        try {
            Long save = patientService.save(patient);
            redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS, "Patient was successfully saved!"));
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS, "Patient was not saved, try again later!"));
        }
        return "redirect:/patient/index";
    }

    @RequestMapping(value = "/edit/{patientID}", method = RequestMethod.GET)
    public ModelAndView edit_get(@PathVariable Long patientID, HttpSession session) {
        if (!authenticate(session)) {
            return new ModelAndView("login");
        }
        ModelAndView mv = new ModelAndView("patient/update");
        try {
            Patient patient = patientService.findOne(patientID);
            mv.addObject("patient", patient);
            mv.addObject("type", Message.SUCCESS);
            mv.addObject("value", "Patient with ID " + patientID + " is found!");
            return mv;
        } catch (Exception ex) {
            mv = new ModelAndView("patient/index");
            mv.addObject("type", Message.ERROR);
            mv.addObject("value", ex.getMessage());
        }
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/edit/{patientID}", method = RequestMethod.POST)
    public String edit_post(@PathVariable Long patientID, @ModelAttribute("patient") Patient patient, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!authenticate(session)) {
            return "redirect:login";
        }
        try {
            Patient patient1 = patientService.update(patientID, patient);
            redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS, "Patient was successfully updated!"));
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("message", new Message(Message.ERROR, "Patient was not update, try again later!"));
        }
        return "redirect:/patient/index";
    }

    @RequestMapping(value = "/delete/{patientID}", method = RequestMethod.GET)
    public ModelAndView delete_get(@PathVariable Long patientID, HttpSession session) {
        if (!authenticate(session)) {
            return new ModelAndView("login");
        }
        ModelAndView mv = new ModelAndView("patient/delete");
        try {
            Patient patient = patientService.findOne(patientID);
            mv.addObject("patient", patient);
            return mv;
        } catch (Exception ex) {
            mv = new ModelAndView("index");
            mv.addObject("type", Message.ERROR);
            mv.addObject("value", ex.getMessage());
            return mv;
        }
    }

    @RequestMapping(value = "/delete/{patientID}", method = RequestMethod.POST)
    public String delete_post(@PathVariable Long patientID, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!authenticate(session)) {
            return "redirect:login";
        }
        try {
            patientService.delete(patientID);
            redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS, "Patient was successfully deleted!"));
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("message", new Message(Message.ERROR, "Patient was not delete, try again later!"));
        }
        return "redirect:/patient/index";
    }
}
