package com.if26.topuv.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

import com.if26.topuv.R;
import com.if26.topuv.activities.TabActivity;
import com.if26.topuv.constants.IntentConstants;
import com.if26.topuv.models.Description;
import com.if26.topuv.services.DescriptionDetailsService;

import java.util.concurrent.ExecutionException;

/**
 * Created by Flo on 27/12/2013.
 */
public class TabFragment extends Fragment implements OnTabChangeListener {

    private View myView;
    private TabHost myTabHost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.tab_page, container, false);
        myTabHost = (TabHost) myView.findViewById(R.id.tabHost);
        initTabs();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.commit();

        DescriptionFragment description = new DescriptionFragment();
        DetailsFragment details = new DetailsFragment();
        CommentsFragment comments = new CommentsFragment();
        ft.add(R.id.tab1, description);
        ft.add(R.id.tab2, details);
        ft.add(R.id.tab3, comments);

        return myView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        try
        {
            String token = this.getActivity().getIntent().getStringExtra(IntentConstants.TOKEN);
            String id_description = this.getActivity().getIntent().getStringExtra(IntentConstants.ID_DESCRIPTION);
            String id_uv = this.getActivity().getIntent().getStringExtra(IntentConstants.ID_UV);
            String label_uv = this.getActivity().getIntent().getStringExtra(IntentConstants.LABEL_UV);

            DescriptionDetailsService descriptionService = new DescriptionDetailsService();
            Description description = descriptionService.execute(token, id_description).get();
            ((TabActivity) this.getActivity()).setDescription(description);

        }
        catch(InterruptedException interruptedException)
        {

        }
        catch(ExecutionException executionException)
        {

        }
    }

    public void initTabs(){
        myTabHost.setup();
        TabSpec descriptionSpec = myTabHost.newTabSpec("Description");
        descriptionSpec.setContent(R.id.tab1);
        descriptionSpec.setIndicator("Description");

        TabSpec detailsSpec = myTabHost.newTabSpec("Details");
        detailsSpec.setContent(R.id.tab2);
        detailsSpec.setIndicator("Details");

        TabSpec commentsSpec = myTabHost.newTabSpec("Commentaires");
        commentsSpec.setContent(R.id.tab3);
        commentsSpec.setIndicator("Commentaires");

        myTabHost.addTab(descriptionSpec);
        myTabHost.addTab(detailsSpec);
        myTabHost.addTab(commentsSpec);
    }

    @Override
    public void onTabChanged(String s) {

    }
}
