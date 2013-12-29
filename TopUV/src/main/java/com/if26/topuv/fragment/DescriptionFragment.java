package com.if26.topuv.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.if26.topuv.R;

/**
 * Created by Flo on 27/12/2013.
 */
public class DescriptionFragment extends Fragment implements View.OnTouchListener {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.description_page, container, false);
        RatingBar ratingBar = (RatingBar) v.findViewById(R.id.ratingBar);
        ratingBar.setFocusable(false);
        ratingBar.setOnTouchListener(this);

        return v;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return true;
    }
}