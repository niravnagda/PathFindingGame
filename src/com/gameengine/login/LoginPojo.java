/*
 * @(#)LoginPojo.java   13/10/20
 *
 * Copyright (c) 2005 your company name
 *
 * License agreement text here ...
 *
 *
 *
 */



package com.gameengine.login;

/**
 * Description: Storing the Login data retrieved from the user.
 * @author JaSon
 *
 */
public class LoginPojo {
    private String username;
    private String password;
    private int    id;

    /**
     * Accessors and Mutators for user credentials.
     *
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}


//~ Formatted by Jindent --- http://www.jindent.com
