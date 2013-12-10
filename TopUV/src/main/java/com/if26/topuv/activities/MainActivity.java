package com.if26.topuv.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;


import com.if26.topuv.R;
import com.if26.topuv.fragment.LoginFragment;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.commit();

        LoginFragment lf = new LoginFragment();
        ft.add(R.id.myFragment, lf);
    }
}
