package com.if26.topuv.models;

/**
 * Created by Jules on 17/12/13.
 */
public class Uv {
    public String id;
    public String label;
    public String id_description;
    public String id_category;

    public Category category;

    public String getListLabel(){
        return "  " + this.id + " - " + this.label;
    }
}
