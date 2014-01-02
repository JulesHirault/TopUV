package com.if26.topuv.fragment;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.if26.topuv.R;
import com.if26.topuv.activities.TabActivity;
import com.if26.topuv.constants.IntentConstants;
import com.if26.topuv.models.Uv;
import com.if26.topuv.services.ListService;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Flo on 23/12/2013.
 */
public class ListUv extends ListFragment {

    private Context context;

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        try
        {
            context = this.getActivity();
            String token = this.getActivity().getIntent().getStringExtra(IntentConstants.TOKEN);
            String id_category = this.getActivity().getIntent().getStringExtra(IntentConstants.ID_CATEGORY);

            ListService listService = new ListService(context);
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

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        String token = this.getActivity().getIntent().getStringExtra(IntentConstants.TOKEN);
        String student_id = this.getActivity().getIntent().getStringExtra(IntentConstants.STUDENT_ID);
        Uv uv = (Uv) this.getListAdapter().getItem(position);

        Intent intent = new Intent(this.getActivity(), TabActivity.class);
        intent.putExtra(IntentConstants.TOKEN, token);
        intent.putExtra(IntentConstants.STUDENT_ID, student_id);
        intent.putExtra(IntentConstants.ID_UV, uv.id);
        intent.putExtra(IntentConstants.LABEL_UV, uv.label);
        intent.putExtra(IntentConstants.ID_DESCRIPTION, uv.id_description);

        this.startActivity(intent);
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
