package com.if26.topuv.activities;

import android.app.Activity;
import android.os.Bundle;

import com.if26.topuv.R;

/**
 * Activité qui lance le fragment permettant d'afficher la liste résultant du choix de catégorie effectué
 */
public class ListActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_uv);
    }
}
