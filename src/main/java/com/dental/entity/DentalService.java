/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dental.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Katarina Djordjevic
 */
@Entity
@Table(name = "dental_service")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DentalService.findAll", query = "SELECT d FROM DentalService d"),
    @NamedQuery(name = "DentalService.findByDentalserviceID", query = "SELECT d FROM DentalService d WHERE d.dentalserviceID = :dentalserviceID"),
    @NamedQuery(name = "DentalService.findByName", query = "SELECT d FROM DentalService d WHERE d.name = :name"),
    @NamedQuery(name = "DentalService.findByCost", query = "SELECT d FROM DentalService d WHERE d.cost = :cost"),
    @NamedQuery(name = "DentalService.findByVat", query = "SELECT d FROM DentalService d WHERE d.vat = :vat")})
public class DentalService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Dental_serviceID")
    private Long dentalserviceID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cost")
    private double cost;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Vat")
    private double vat;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dentalService")
    private List<Intervention> interventionList;

    public DentalService() {
    }

    public DentalService(Long dentalserviceID) {
        this.dentalserviceID = dentalserviceID;
    }

    public DentalService(Long dentalserviceID, String name, double cost, double vat) {
        this.dentalserviceID = dentalserviceID;
        this.name = name;
        this.cost = cost;
        this.vat = vat;
    }

    public Long getDentalserviceID() {
        return dentalserviceID;
    }

    public void setDentalserviceID(Long dentalserviceID) {
        this.dentalserviceID = dentalserviceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
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
        hash += (dentalserviceID != null ? dentalserviceID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DentalService)) {
            return false;
        }
        DentalService other = (DentalService) object;
        if ((this.dentalserviceID == null && other.dentalserviceID != null) || (this.dentalserviceID != null && !this.dentalserviceID.equals(other.dentalserviceID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getName();
    }

}
