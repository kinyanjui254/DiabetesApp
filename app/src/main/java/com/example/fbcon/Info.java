package com.example.fbcon;

public class Info {


    private String fname;
    private String lname;
    private String age;
    private String addres;
    private String weit;

    public Info() {

    }

    public Info(String fname, String lname, String age, String addres, String weit) {
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.addres = addres;
        this.weit = weit;

    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getWeit() {
        return weit;
    }

    public void setWeit(String weit) {
        this.weit = weit;
    }
}

