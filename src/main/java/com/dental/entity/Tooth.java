/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dental.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "tooth")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tooth.findAll", query = "SELECT t FROM Tooth t"),
    @NamedQuery(name = "Tooth.findByToothID", query = "SELECT t FROM Tooth t WHERE t.toothID = :toothID"),
    @NamedQuery(name = "Tooth.findByLabel", query = "SELECT t FROM Tooth t WHERE t.label = :label")})
public class Tooth implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ToothID")
    private Long toothID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Label")
    private String label;
    @OneToMany(mappedBy = "tooth")
    private List<Intervention> interventionList;

    public Tooth() {
    }

    public Tooth(Long toothID) {
        this.toothID = toothID;
    }

    public Tooth(Long toothID, String label) {
        this.toothID = toothID;
        this.label = label;
    }

    public Long getToothID() {
        return toothID;
    }

    public void setToothID(Long toothID) {
        this.toothID = toothID;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
        hash += (toothID != null ? toothID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tooth)) {
            return false;
        }
        Tooth other = (Tooth) object;
        if ((this.toothID == null && other.toothID != null) || (this.toothID != null && !this.toothID.equals(other.toothID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getLabel();
    }

}
