package com.if26.topuv.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.if26.topuv.R;
import com.if26.topuv.activities.TabActivity;
import com.if26.topuv.constants.IntentConstants;
import com.if26.topuv.models.Description;
import com.if26.topuv.services.DescriptionDetailsService;
import java.util.concurrent.ExecutionException;

/**
 * Created by Flo on 27/12/2013.
 */
public class DescriptionFragment extends Fragment implements View.OnTouchListener {

    View myView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.description_page, container, false);


        return myView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        String id_uv = this.getActivity().getIntent().getStringExtra(IntentConstants.ID_UV);
        String label_uv = this.getActivity().getIntent().getStringExtra(IntentConstants.LABEL_UV);

        Description description = ((TabActivity)this.getActivity()).getDescription();

        RatingBar ratingBar = (RatingBar) myView.findViewById(R.id.ratingBar);
        TextView uvTitleTextView = (TextView) myView.findViewById(R.id.uvTitleTextView);
        TextView prgTextView = (TextView) myView.findViewById(R.id.prgTextView);
        TextView objectifsTextView = (TextView) myView.findViewById(R.id.objectifsTextView);


        if((id_uv != null) && (label_uv != null)){
            uvTitleTextView.setText(id_uv + " - " + label_uv);
        }

        ratingBar.setFocusable(false);
        ratingBar.setOnTouchListener(this);
        ratingBar.setRating(((float) description.avg_mark) / 4);

        if(description.curricula != null){
            prgTextView.setText(description.curricula);
        }

        if(description.objectives != null){
            objectifsTextView.setText(description.objectives);
        }


    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return true;
    }
}