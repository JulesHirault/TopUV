package com.if26.topuv.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;


import com.if26.topuv.R;
import com.if26.topuv.fragment.LoginFragment;

/**
 * Activité principale de l'application. Elle lance le fragment permettant de e logger
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
