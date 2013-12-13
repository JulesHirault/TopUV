package com.if26.topuv.models;

/**
 * Created by Flo on 12/12/2013.
 */
public class Student {
    public String id;
    public String login;
    public String password;
    public String name;
    public String surname;
    public String token;

    public String getIdentity()
    {
        return this.name + " " + this.surname;
    }
}
