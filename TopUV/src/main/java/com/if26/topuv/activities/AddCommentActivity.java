package com.if26.topuv.activities;

import android.app.Activity;
import android.os.Bundle;

import com.if26.topuv.R;

/**
 * Activité qui permet qui lance le fragment permettant d'ajouter un commentaire
 */
public class AddCommentActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
    }
}
