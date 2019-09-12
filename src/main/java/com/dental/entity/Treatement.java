/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dental.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Katarina Djordjevic
 */
@Entity
@Table(name = "treatement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Treatement.findAll", query = "SELECT t FROM Treatement t"),
    @NamedQuery(name = "Treatement.findByTreatementID", query = "SELECT t FROM Treatement t WHERE t.treatementID = :treatementID"),
    @NamedQuery(name = "Treatement.findByDate", query = "SELECT t FROM Treatement t WHERE t.date = :date"),
    @NamedQuery(name = "Treatement.findByNote", query = "SELECT t FROM Treatement t WHERE t.note = :note"),
    @NamedQuery(name = "Treatement.findByCost", query = "SELECT t FROM Treatement t WHERE t.cost = :cost")})
public class Treatement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TreatementID")
    private Long treatementID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "Note")
    private String note;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cost")
    private double cost;
    @JoinColumn(name = "Patient_FK", referencedColumnName = "PatientID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Patient patient;
    @JoinColumn(name = "Dentist_FK", referencedColumnName = "DentistID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Dentist dentist;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "treatementFK", fetch = FetchType.EAGER)
    private List<Intervention> interventionList;

    public Treatement() {
        interventionList = new LinkedList<>();
    }

    public Treatement(Long treatementID) {
        this.treatementID = treatementID;
    }

    public Treatement(Long treatementID, Date date, String note, double cost) {
        this.treatementID = treatementID;
        this.date = date;
        this.note = note;
        this.cost = cost;
    }

    public Long getTreatementID() {
        return treatementID;
    }

    public void setTreatementID(Long treatementID) {
        this.treatementID = treatementID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }

    @XmlTransient
    public List<Intervention> getInterventionList() {
        return interventionList;
    }

    public void setInterventionList(List<Intervention> interventionList) {
        this.interventionList = interventionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (treatementID != null ? treatementID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Treatement)) {
            return false;
        }
        Treatement other = (Treatement) object;
        if ((this.treatementID == null && other.treatementID != null) || (this.treatementID != null && !this.treatementID.equals(other.treatementID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dental.entity.Treatement[ treatementID=" + treatementID + " ]";
    }

}
