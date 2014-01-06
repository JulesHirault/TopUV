package com.if26.topuv.activities;

import android.os.Bundle;

import android.app.Activity;
import com.if26.topuv.R;

/**
 * Activité qui permet de lancer le fragment qui initialise les différentes catégories d'UVs disponibles
 */
public class CategoriesActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
    }
}
