package com.if26.topuv.models;

import android.graphics.Bitmap;

/**
 * Classe Student suivant la table
 */
public class Student {
    public int id;
    public String login;
    public String password;
    public String name;
    public String surname;
    public Bitmap picture;
    public String token;

    /**
     * Permet de récupérer l'identité de l'étudiant
     * @return L'identité de l'étudiant
     */
    public String getIdentity()
    {
        return this.name + " " + this.surname;
    }
}
