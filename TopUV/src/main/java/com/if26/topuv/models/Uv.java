package com.if26.topuv.models;

/**
 * Created by Flo on 23/12/2013.
 */
public class Uv {
    public String id;
    public String label;
    public String id_description;
    public String avg_mark;
    public String id_category;

    public Category category;

    public String getListLabel(){
        return "  " + this.id + " - " + this.label;
    }
}
