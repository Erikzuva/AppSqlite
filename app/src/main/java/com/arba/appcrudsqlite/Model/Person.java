package com.arba.appcrudsqlite.Model;

/**
 * Created by Erik on 19/10/2017.
 */

public class Person {
    private int id;
    private String name;
    private String email;
    private String phone;
    private int gender;

    public Person() {
    }

    public Person(int id, String name, String email, String phone, int gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
