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
@Table(name = "dentist")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dentist.findAll", query = "SELECT d FROM Dentist d"),
    @NamedQuery(name = "Dentist.findByDentistID", query = "SELECT d FROM Dentist d WHERE d.dentistID = :dentistID"),
    @NamedQuery(name = "Dentist.findByFirstname", query = "SELECT d FROM Dentist d WHERE d.firstname = :firstname"),
    @NamedQuery(name = "Dentist.findByLastname", query = "SELECT d FROM Dentist d WHERE d.lastname = :lastname"),
    @NamedQuery(name = "Dentist.findBySex", query = "SELECT d FROM Dentist d WHERE d.sex = :sex"),
    @NamedQuery(name = "Dentist.findByUsername", query = "SELECT d FROM Dentist d WHERE d.username = :username"),
    @NamedQuery(name = "Dentist.findByPassword", query = "SELECT d FROM Dentist d WHERE d.password = :password"),
    @NamedQuery(name = "Dentist.findByRole", query = "SELECT d FROM Dentist d WHERE d.role = :role"),
    @NamedQuery(name = "Dentist.findByPhonenumber", query = "SELECT d FROM Dentist d WHERE d.phonenumber = :phonenumber"),
    @NamedQuery(name = "Dentist.findByCardnumber", query = "SELECT d FROM Dentist d WHERE d.cardnumber = :cardnumber")})
public class Dentist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DentistID")
    private Long dentistID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "First_name")
    private String firstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Last_name")
    private String lastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Sex")
    private String sex;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Password")
    private String password;
    @Size(max = 50)
    @Column(name = "Role")
    private String role;
    @Size(max = 10)
    @Column(name = "Phone_number")
    private String phonenumber;
    @Size(max = 9)
    @Column(name = "Card_number")
    private String cardnumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dentist")
    private List<Treatement> treatementList;

    public Dentist() {
    }

    public Dentist(Long dentistID) {
        this.dentistID = dentistID;
    }

    public Dentist(Long dentistID, String firstname, String lastname, String sex, String username, String password) {
        this.dentistID = dentistID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.sex = sex;
        this.username = username;
        this.password = password;
    }

    public Long getDentistID() {
        return dentistID;
    }

    public void setDentistID(Long dentistID) {
        this.dentistID = dentistID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    @XmlTransient
    public List<Treatement> getTreatementList() {
        return treatementList;
    }

    public void setTreatementList(List<Treatement> treatementList) {
        this.treatementList = treatementList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dentistID != null ? dentistID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dentist)) {
            return false;
        }
        Dentist other = (Dentist) object;
        if ((this.dentistID == null && other.dentistID != null) || (this.dentistID != null && !this.dentistID.equals(other.dentistID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dr. " + getFirstname() + " " + getLastname();
    }

}
