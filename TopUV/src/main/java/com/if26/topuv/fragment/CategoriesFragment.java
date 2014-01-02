package com.if26.topuv.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.if26.topuv.R;
import com.if26.topuv.activities.ListActivity;
import com.if26.topuv.activities.SearchActivity;
import com.if26.topuv.constants.IntentConstants;


/**
 * Created by Flo on 13/12/2013.
 */
public class CategoriesFragment extends Fragment implements View.OnClickListener {

    private ImageView CS, TM, ME, CT, EC, Stages, Autre, Top, Flop, MesUV, Search;
    private Context context;

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
        MesUV = (ImageView) v.findViewById(R.id.MesUV);
        Search = (ImageView) v.findViewById(R.id.Search);
        CS.setOnClickListener(this);
        TM.setOnClickListener(this);
        ME.setOnClickListener(this);
        CT.setOnClickListener(this);
        EC.setOnClickListener(this);
        Stages.setOnClickListener(this);
        Autre.setOnClickListener(this);
        Top.setOnClickListener(this);
        Flop.setOnClickListener(this);
        Search.setOnClickListener(this);
        MesUV.setOnClickListener(this);
        context = this.getActivity();

        return v;
    }

    @Override
    public void onClick(View view) {
        String token = this.getActivity().getIntent().getStringExtra(IntentConstants.TOKEN);
        String student_id = this.getActivity().getIntent().getStringExtra(IntentConstants.STUDENT_ID);
        Intent intent = null;

        if(view == CS){
            intent = new Intent(this.getActivity(), ListActivity.class);
            intent.putExtra(IntentConstants.ID_CATEGORY, "1");
        } else if(view == TM) {
            intent = new Intent(this.getActivity(), ListActivity.class);
            intent.putExtra(IntentConstants.ID_CATEGORY, "2");
        } else if(view == ME) {
            intent = new Intent(this.getActivity(), ListActivity.class);
            intent.putExtra(IntentConstants.ID_CATEGORY, "4");
        } else if(view == CT) {
            intent = new Intent(this.getActivity(), ListActivity.class);
            intent.putExtra(IntentConstants.ID_CATEGORY, "3");
        } else if(view == EC) {
            intent = new Intent(this.getActivity(), ListActivity.class);
            intent.putExtra(IntentConstants.ID_CATEGORY, "5");
        } else if(view == Stages) {
            intent = new Intent(this.getActivity(), ListActivity.class);
            intent.putExtra(IntentConstants.ID_CATEGORY, "6");
        } else if(view == Autre) {
            intent = new Intent(this.getActivity(), ListActivity.class);
            intent.putExtra(IntentConstants.ID_CATEGORY, "7");
        } else if(view == Top) {
            Toast.makeText(getActivity().getBaseContext(), "Get Top List", Toast.LENGTH_SHORT).show();
        } else if(view == Flop) {
            Toast.makeText(getActivity().getBaseContext(), "Get Flop List", Toast.LENGTH_SHORT).show();
        } else if(view == MesUV) {
            intent = new Intent(this.getActivity(), ListActivity.class);
            intent.putExtra(IntentConstants.ID_CATEGORY, "0");
        } else if(view == Search) {
            intent = new Intent(this.getActivity(), SearchActivity.class);
            intent.putExtra(IntentConstants.ID_CATEGORY, "8");
        }
        intent.putExtra(IntentConstants.STUDENT_ID, student_id);
        intent.putExtra(IntentConstants.TOKEN, token);
        this.startActivity(intent);


    }

}
