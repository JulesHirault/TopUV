package com.if26.topuv.activities;

import android.app.Activity;
import android.os.Bundle;

import com.if26.topuv.R;

/**
 * Activité qui lance le fragment permettant de rechercher une Uv particulière
 */
public class SearchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
}
