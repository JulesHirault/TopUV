package com.if26.topuv.models;

/**
 * Classe Uv correspondant à la table
 */
public class Uv {
    public String id;
    public String label;
    public String id_description;
    public String id_category;

    public Category category;

    /**
     * Permet de récupérer le nom de L'Uv
     * @return le nom de l'Uv
     */
    public String getListLabel(){
        return "  " + this.id + " - " + this.label;
    }
}
