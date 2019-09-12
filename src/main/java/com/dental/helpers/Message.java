/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dental.helpers;

import java.io.Serializable;

/**
 *
 * @author Katarina Djordjevic
 */
public class Message implements Serializable {

    public static final String ERROR = "danger";
    public static final String SUCCESS = "success";
    public static final String INFO = "warning";
    public String type;
    public String value;

    public Message(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public Message() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
