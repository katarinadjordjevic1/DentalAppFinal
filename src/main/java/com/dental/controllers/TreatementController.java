/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dental.controllers;

import com.dental.entity.DentalService;
import com.dental.entity.Intervention;
import com.dental.entity.Patient;
import com.dental.entity.Tooth;
import com.dental.entity.Treatement;
import com.dental.entity.User;
import com.dental.helpers.Message;
import com.dental.service.IDentalService;
import com.dental.service.IPatientService;
import com.dental.service.IToothService;
import com.dental.service.ITreatementService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Katarina Djordjevic
 */
@Controller
public class TreatementController extends AuthenticateController {

    @Autowired
    ITreatementService treatementService;
    @Autowired
    private IPatientService patientService;
    @Autowired
    private IToothService toothService;
    @Autowired
    private IDentalService dentalService;

    private final Map<Integer, Intervention> map = new HashMap<>();

    @RequestMapping(value = "/treatement/index", method = RequestMethod.GET)
    public ModelAndView getAllTreatment(Model model, HttpSession session) {
        if (!authenticate(session)) {
            return new ModelAndView("login");
        }
        ModelAndView modelAndView = new ModelAndView("treatement/index");
        if (model.asMap().get("message") != null) {
            modelAndView.addObject("value", ((Message) model.asMap().get("message")).getValue());
            modelAndView.addObject("type", ((Message) model.asMap().get("message")).getType());
        }
        try {
            List<Treatement> treatements = treatementService.findAll();
            modelAndView.addObject("treatementList", treatements);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            modelAndView.addObject("treatementList", new LinkedList<Patient>());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/treatement/details/{treatementID}", method = RequestMethod.GET)
    public ModelAndView details(@PathVariable Long treatementID, HttpSession session) {
        if (!authenticate(session)) {
            return new ModelAndView("login");
        }
        ModelAndView modelAndView = new ModelAndView("treatement/details");
        try {
            Treatement treatement = treatementService.findOne(treatementID);
            modelAndView.addObject("treatement", treatement);
            modelAndView.addObject("type", Message.SUCCESS);
            modelAndView.addObject("value", "Treatement with ID " + treatementID + " is found!");
        } catch (Exception ex) {
            modelAndView = new ModelAndView("treatement/index");
            modelAndView.addObject("type", Message.ERROR);
            modelAndView.addObject("value", ex.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/patient/{patientID}/treatement/insert", method = RequestMethod.GET)
    public ModelAndView insert_get(@PathVariable Long patientID, HttpSession session) {
        try {
            if (!authenticate(session)) {
                return new ModelAndView("login");
            }
            ModelAndView mv = new ModelAndView("treatement/insert");
            List<Tooth> toothList = treatementService.findAllTooth();
            List<DentalService> dentalServiceList = treatementService.findAllService();

            mv.addObject("treatement", new Treatement());
            mv.addObject("patient", patientService.findOne(patientID));
            mv.addObject("toothList", toothList);
            mv.addObject("dentalServiceList", dentalServiceList);
            return mv;
        } catch (Exception ex) {
            return new ModelAndView("patient/index", "message", new Message(Message.ERROR, ex.getMessage()));
        }
    }

    @RequestMapping(value = "/patient/{patientID}/treatement/insert", method = RequestMethod.POST)
    public String insert_post(@RequestParam("date") String date, @RequestParam("note") String note, @PathVariable Long patientID, HttpSession session, final RedirectAttributes redirectAttributes) {
        if (!authenticate(session)) {
            return "redirect:login";
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date d = sdf.parse(date);
            System.out.println("Date: " + date);
            System.out.println("Note: " + note);
            Treatement treatement = new Treatement();
            treatement.setNote(note);
            treatement.setDate(d);
            Patient patient = patientService.findOne(patientID);
            User user = (User) session.getAttribute("user");
            treatement.setPatient(patient);
            for (Map.Entry<Integer, Intervention> entry : map.entrySet()) {
                treatement.getInterventionList().add(entry.getValue());
            }
            treatementService.save(treatement, user);
            redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS, "Treatement was successfully saved!"));
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("message", new Message(Message.SUCCESS, "Treatement was not saved, try again later!"));
        }
        return "redirect:/treatement/index";
    }

    @RequestMapping(value = "/ajax/intervention/insert", method = RequestMethod.POST)
    public @ResponseBody
    String insertInterventionPost(@RequestParam String description, @RequestParam Long tooth, @RequestParam Long service, @RequestParam Integer rbr) throws Exception {
        try {
            Tooth toothEntity = toothService.findOne(tooth);
            DentalService dentalServiceEntity = dentalService.findOne(service);
            Intervention intervention = new Intervention();
            intervention.setDescription(description);
            intervention.setDentalService(dentalServiceEntity);
            intervention.setTooth(toothEntity);

            map.put(rbr, intervention);
            return "<tr id=\"" + rbr + "\"><td id=\"description_" + rbr + "\">" + description + "</td><td>" + intervention.getDentalService().getName() + "</td><td>" + (intervention.getTooth() == null ? "---" : intervention.getTooth().getLabel()) + "</td>"
                    + "<td><div class=\"row\"> <a onclick=\"editRow(+" + rbr + ")\" class=\"btn btn-primary\"><span class='glyphicon glyphicon-pencil'></span></a> "
                    + "<a onclick=\"deleteRow(+" + rbr + ")\" class=\"btn btn-danger\"><span class='glyphicon glyphicon-trash'></span></a></div></td>"
                    + "<input type=\"hidden\" id=\"toothID_" + rbr + "\" value=\"" + tooth + "\">"
                    + "<input type=\"hidden\" id=\"serviceID_" + rbr + "\" value=\"" + service + "\"></tr>";
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }

    }

    @RequestMapping(value = "/ajax/intervention/delete", method = RequestMethod.POST)
    public @ResponseBody
    String deleteIntervention(@RequestParam Integer rbr) {
        if (!map.containsKey(rbr)) {
            return "Can't delete row, it doesn't exist.";
        }
        map.remove(rbr);
        System.out.println("Obrisan");
        return "Successfully delete row!";
    }

    @RequestMapping(value = "/ajax/intervention/update", method = RequestMethod.POST)
    public @ResponseBody
    String updateInterventionPost(@RequestParam String description, @RequestParam Long tooth, @RequestParam Long service, @RequestParam Integer rbr) throws Exception {
        try {
       
            System.out.println(description + " " + tooth + " " + service);

            Tooth toothEntity = toothService.findOne(tooth);
            DentalService dentalServiceEntity = dentalService.findOne(service);
            Intervention intervention = new Intervention();
            intervention.setDescription(description);
            intervention.setDentalService(dentalServiceEntity);
            intervention.setTooth(toothEntity);

            map.put(rbr, intervention);
            return "Successfully updated row";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }
}
