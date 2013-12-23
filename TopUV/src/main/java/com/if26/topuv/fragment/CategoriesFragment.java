package com.if26.topuv.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.if26.topuv.R;
import com.if26.topuv.activities.CategoriesActivity;
import com.if26.topuv.activities.ListActivity;
import com.if26.topuv.constants.IntentConstants;

import java.util.concurrent.ExecutionException;

/**
 * Created by Flo on 13/12/2013.
 */
public class CategoriesFragment extends Fragment implements View.OnClickListener {

    private ImageView CS, TM, ME, CT, EC, Stages, Autre, Top, Flop, MesUV, Search;

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

        return v;
    }

    @Override
    public void onClick(View view) {
        String token = this.getActivity().getIntent().getStringExtra(IntentConstants.TOKEN);

        if(view == CS){
            Intent intent = new Intent(this.getActivity(), ListActivity.class);
            intent.putExtra(IntentConstants.ID_CATEGORY, "1");
            intent.putExtra(IntentConstants.TOKEN, token);
            this.startActivity(intent);
        } else if(view == TM) {
            Intent intent = new Intent(this.getActivity(), ListActivity.class);
            intent.putExtra(IntentConstants.ID_CATEGORY, "2");
            intent.putExtra(IntentConstants.TOKEN, token);
            this.startActivity(intent);
        } else if(view == ME) {
            Intent intent = new Intent(this.getActivity(), ListActivity.class);
            intent.putExtra(IntentConstants.ID_CATEGORY, "4");
            intent.putExtra(IntentConstants.TOKEN, token);
            this.startActivity(intent);
        } else if(view == CT) {
            Intent intent = new Intent(this.getActivity(), ListActivity.class);
            intent.putExtra(IntentConstants.ID_CATEGORY, "3");
            intent.putExtra(IntentConstants.TOKEN, token);
            this.startActivity(intent);
        } else if(view == EC) {
            Intent intent = new Intent(this.getActivity(), ListActivity.class);
            intent.putExtra(IntentConstants.ID_CATEGORY, "5");
            intent.putExtra(IntentConstants.TOKEN, token);
            this.startActivity(intent);
        } else if(view == Stages) {
            Intent intent = new Intent(this.getActivity(), ListActivity.class);
            intent.putExtra(IntentConstants.ID_CATEGORY, "6");
            intent.putExtra(IntentConstants.TOKEN, token);
            this.startActivity(intent);
        } else if(view == Autre) {
            Intent intent = new Intent(this.getActivity(), ListActivity.class);
            intent.putExtra(IntentConstants.ID_CATEGORY, "7");
            intent.putExtra(IntentConstants.TOKEN, token);
            this.startActivity(intent);
        } else if(view == Top) {
            Toast.makeText(getActivity().getBaseContext(), "Get Top List", Toast.LENGTH_SHORT).show();
        } else if(view == Flop) {
            Toast.makeText(getActivity().getBaseContext(), "Get Flop List", Toast.LENGTH_SHORT).show();
        } else if(view == MesUV) {
            Toast.makeText(getActivity().getBaseContext(), "Get MesUV List", Toast.LENGTH_SHORT).show();
        } else if(view == Search) {
            Toast.makeText(getActivity().getBaseContext(), "Get Search List", Toast.LENGTH_SHORT).show();
        }
    }

}
