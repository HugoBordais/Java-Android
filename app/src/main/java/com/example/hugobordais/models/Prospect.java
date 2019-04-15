package com.example.hugobordais.models;

import java.io.Serializable;

public class Prospect implements Serializable {

    private String name;
    private String firstname;
    private String addresse1;
    private String addresse2;
    private String postCode;
    private String mobile;
    private String city;
    private String mail;
    private String companyName;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAddresse1() {
        return addresse1;
    }

    public void setAddresse1(String addresse1) {
        this.addresse1 = addresse1;
    }

    public String getAddresse2() {
        return addresse2;
    }

    public void setAddresse2(String addresse2) {
        this.addresse2 = addresse2;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
