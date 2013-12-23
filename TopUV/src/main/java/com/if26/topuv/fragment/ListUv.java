package com.if26.topuv.fragment;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.if26.topuv.R;
import com.if26.topuv.constants.IntentConstants;
import com.if26.topuv.models.Uv;
import com.if26.topuv.services.ListService;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Flo on 23/12/2013.
 */
public class ListUv extends ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        try
        {
            String token = this.getActivity().getIntent().getStringExtra(IntentConstants.TOKEN);
            String id_category = this.getActivity().getIntent().getStringExtra(IntentConstants.ID_CATEGORY);

            ListService listService = new ListService();
            ArrayList<Uv> uvs = listService.execute(token, id_category).get();

            this.setListAdapter(new UvsAdapter(this.getActivity(), uvs));
        }
        catch(InterruptedException interruptedException)
        {

        }
        catch(ExecutionException executionException)
        {

        }
    }


    private class UvsAdapter extends ArrayAdapter<Uv>
    {
        public UvsAdapter(Context context, ArrayList<Uv> uvs)
        {
            super(context, R.layout.list_uv, uvs);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this.getContext()).inflate(R.layout.list_uv, null);

            ((TextView) viewGroup.findViewById(R.id.uv)).setText(this.getItem(position).getListLabel());

            return viewGroup;
        }
    }

}