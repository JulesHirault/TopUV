package com.if26.topuv.activities;

import android.app.Activity;
import android.os.Bundle;

import com.if26.topuv.R;
import com.if26.topuv.models.Comment;
import com.if26.topuv.models.Description;

import java.util.ArrayList;

/**
 * Created by Flo on 27/12/2013.
 */
public class TabActivity extends Activity {

    Description description;

    ArrayList<Comment> comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_page);
    }

    public Description getDescription(){
        return description;
    }

    public void setDescription(Description description){
        this.description = description;
    }


    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
