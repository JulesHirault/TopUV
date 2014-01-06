package com.if26.topuv.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.if26.topuv.R;
import com.if26.topuv.activities.TabActivity;
import com.if26.topuv.constants.IntentConstants;
import com.if26.topuv.models.Description;

/**
 * Fragment qui va simplement récupérer le détail des horaires et de la disponibilité de l'Uv
 */
public class DetailsFragment extends Fragment {

    View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.details_page, container, false);

        return myView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        Description description = ((TabActivity)this.getActivity()).getDescription();


        TextView typeTextView = (TextView) myView.findViewById(R.id.typeTextView);
        TextView creditsTextView = (TextView) myView.findViewById(R.id.creditsTextView);
        TextView cmTextView = (TextView) myView.findViewById(R.id.cmTextView);
        TextView tdTextView = (TextView) myView.findViewById(R.id.tdTextView);
        TextView tpTextView = (TextView) myView.findViewById(R.id.tpTextView);
        TextView tpeTextView = (TextView) myView.findViewById(R.id.tpeTextView);
        TextView dispoTextView = (TextView) myView.findViewById(R.id.dispoTextView);

        // vérification si les informations ont bien été renseignées
        if(description.type_uv != null){
            typeTextView.setText(description.type_uv);
        }

        if(description.credit != null){
            creditsTextView.setText(description.credit);
        }

        if(description.lectures != null){
            cmTextView.setText(description.lectures);
        }

        if(description.tutorials != null){
            tdTextView.setText(description.tutorials);
        }

        if(description.practicals != null){
            tpTextView.setText(description.practicals);
        }

        if(description.personnal != null){
            tpeTextView.setText(description.personnal);
        }

        if(description.availibility != null){
            dispoTextView.setText(description.availibility);
        }
    }
}
