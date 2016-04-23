package com.codelabs.codelabsevent.network.model;

/**
 * Created by mexanjuadha on 4/22/16.
 */
public class User {

    private int id;

    private String firstname;

    private String lastname;

    private String email;

    private String telephone;

    public User(int id, String firstname, String lastname, String email, String telephone) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }
}
