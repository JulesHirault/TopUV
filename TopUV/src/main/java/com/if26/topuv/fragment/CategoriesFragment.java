package com.if26.topuv.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.if26.topuv.R;

import java.util.concurrent.ExecutionException;

/**
 * Created by Flo on 13/12/2013.
 */
public class CategoriesFragment extends Fragment implements View.OnClickListener {

    private ImageView CS, TM, ME, CT, EC, Stages, Autre, Top, Flop;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.categories_page, container, false);
        CS = (ImageView) v.findViewById(R.id.CS);
        TM = (ImageView) v.findViewById(R.id.TM);
        ME = (ImageView) v.findViewById(R.id.ME);
        CT = (ImageView) v.findViewById(R.id.CT);
        EC = (ImageView) v.findViewById(R.id.EC);
        Stages = (ImageView) v.findViewById(R.id.Stages);
        Autre = (ImageView) v.findViewById(R.id.Autre);
        Top = (ImageView) v.findViewById(R.id.Top);
        Flop = (ImageView) v.findViewById(R.id.Flop);
        CS.setOnClickListener(this);
        TM.setOnClickListener(this);
        ME.setOnClickListener(this);
        CT.setOnClickListener(this);
        EC.setOnClickListener(this);
        Stages.setOnClickListener(this);
        Autre.setOnClickListener(this);
        Top.setOnClickListener(this);
        Flop.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        if(view == CS){
            Toast.makeText(getActivity().getBaseContext(), "Get CS List", Toast.LENGTH_SHORT).show();
        } else if(view == TM) {
            Toast.makeText(getActivity().getBaseContext(), "Get TM List", Toast.LENGTH_SHORT).show();
        } else if(view == ME) {
            Toast.makeText(getActivity().getBaseContext(), "Get ME List", Toast.LENGTH_SHORT).show();
        } else if(view == CT) {
            Toast.makeText(getActivity().getBaseContext(), "Get CT List", Toast.LENGTH_SHORT).show();
        } else if(view == EC) {
            Toast.makeText(getActivity().getBaseContext(), "Get EC List", Toast.LENGTH_SHORT).show();
        } else if(view == Stages) {
            Toast.makeText(getActivity().getBaseContext(), "Get Stages List", Toast.LENGTH_SHORT).show();
        } else if(view == Autre) {
            Toast.makeText(getActivity().getBaseContext(), "Get Autre List", Toast.LENGTH_SHORT).show();
        } else if(view == Top) {
            Toast.makeText(getActivity().getBaseContext(), "Get Top List", Toast.LENGTH_SHORT).show();
        } else if(view == Flop) {
            Toast.makeText(getActivity().getBaseContext(), "Get Flop List", Toast.LENGTH_SHORT).show();
        }
    }

}
