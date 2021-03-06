package com.if26.topuv.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.if26.topuv.R;
import com.if26.topuv.activities.ListActivity;
import com.if26.topuv.constants.IntentConstants;


/**
 * Fragment qui va permettre la recherche d'une Uv particulière
 */
public class SearchFragment extends Fragment implements View.OnClickListener {

    private EditText searchText;
    private Button searchButton;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.search_page, container, false);
        searchText = (EditText) v.findViewById(R.id.searchField);
        searchButton = (Button) v.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(this);
        context = this.getActivity();

        return v;
    }

    @Override
    public void onClick(View view) {

        boolean error = false;
        // validation locale
        if(searchText.length() == 0)
        {
            error = true;
        }
        if(error)
        {
            Toast.makeText(getActivity().getBaseContext(), "Blank search field !", Toast.LENGTH_SHORT).show();
            return;
        }

        String token = this.getActivity().getIntent().getStringExtra(IntentConstants.TOKEN);
        String id_category = this.getActivity().getIntent().getStringExtra(IntentConstants.ID_CATEGORY);
        String student_id = this.getActivity().getIntent().getStringExtra(IntentConstants.STUDENT_ID);
        Intent intent = new Intent(this.getActivity(), ListActivity.class);
        intent.putExtra(IntentConstants.ID_CATEGORY, id_category);
        intent.putExtra(IntentConstants.ID_UV, searchText.getText().toString());
        intent.putExtra(IntentConstants.STUDENT_ID, student_id);
        intent.putExtra(IntentConstants.TOKEN, token);
        this.startActivity(intent);
        this.getActivity().finish();
    }

}
