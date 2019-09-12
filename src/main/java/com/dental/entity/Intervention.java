/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dental.entity;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Katarina Djordjevic
 */
@Entity
@Table(name = "intervention")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Intervention.findAll", query = "SELECT i FROM Intervention i"),
    @NamedQuery(name = "Intervention.findByInterventionID", query = "SELECT i FROM Intervention i WHERE i.interventionID = :interventionID"),
    @NamedQuery(name = "Intervention.findByDescription", query = "SELECT i FROM Intervention i WHERE i.description = :description")})
public class Intervention implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "InterventionID")
    private Long interventionID;
    @Size(max = 100)
    @Column(name = "Description")
    private String description;
    @JoinColumn(name = "Dental_service_FK", referencedColumnName = "Dental_serviceID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private DentalService dentalService;
    @JoinColumn(name = "Tooth_FK", referencedColumnName = "ToothID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tooth tooth;
    @JoinColumn(name = "Treatement_FK", referencedColumnName = "TreatementID")
    @ManyToOne(optional = false)
    private Treatement treatementFK;

    public Intervention() {
    }

    public Intervention(Long interventionID) {
        this.interventionID = interventionID;
    }

    public Long getInterventionID() {
        return interventionID;
    }

    public void setInterventionID(Long interventionID) {
        this.interventionID = interventionID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DentalService getDentalService() {
        return dentalService;
    }

    public void setDentalService(DentalService dentalService) {
        this.dentalService = dentalService;
    }

    public Tooth getTooth() {
        return tooth;
    }

    public void setTooth(Tooth tooth) {
        this.tooth = tooth;
    }

    public Treatement getTreatementFK() {
        return treatementFK;
    }

    public void setTreatementFK(Treatement treatementFK) {
        this.treatementFK = treatementFK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (interventionID != null ? interventionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Intervention)) {
            return false;
        }
        Intervention other = (Intervention) object;
        if ((this.interventionID == null && other.interventionID != null) || (this.interventionID != null && !this.interventionID.equals(other.interventionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dental.entity.Intervention[ interventionID=" + interventionID + " ]";
    }

}
