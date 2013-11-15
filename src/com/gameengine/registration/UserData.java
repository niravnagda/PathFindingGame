/*
 * @(#)UserData.java   13/10/20
 *
 * Copyright (c) 2005 your company name
 *
 * License agreement text here ...
 *
 *
 *
 */



package com.gameengine.registration;

/**
 * Description: Storing the Registration data retrieved from the user.
 * @author JaSon
 *
 */
public class UserData {
    private String firstName;
    private String lastName;
    private String emailId;
    private String password;
    private String userName;
    private int    id;

    /**
     * Accessors and Mutators for user data.
     *
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
