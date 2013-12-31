package com.if26.topuv.fragment;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost.OnTabChangeListener;

import com.if26.topuv.R;
import com.if26.topuv.activities.TabActivity;
import com.if26.topuv.constants.IntentConstants;
import com.if26.topuv.models.Comment;
import com.if26.topuv.models.Description;
import com.if26.topuv.services.CommentsService;
import com.if26.topuv.services.DescriptionDetailsService;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Flo on 27/12/2013.
 */
public class TabFragment extends Fragment implements OnTabChangeListener {

    private View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        myView = inflater.inflate(R.layout.tab_page, container, false);


        // Notice that setContentView() is not used, because we use the root
        // android.R.id.content as the container for each fragment

        // setup action bar for tabs
        ActionBar actionBar = this.getActivity().getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);

        ActionBar.Tab tab = actionBar.newTab()
                .setText("Description")
                .setTabListener(new TabListener<DescriptionFragment>(
                        this.getActivity(), "Description", DescriptionFragment.class));
        actionBar.addTab(tab);

        tab = actionBar.newTab()
                .setText("Details")
                .setTabListener(new TabListener<DetailsFragment>(
                        this.getActivity(), "album", DetailsFragment.class));
        actionBar.addTab(tab);

        tab = actionBar.newTab()
                .setText("Commentaires")
                .setTabListener(new TabListener<CommentsFragment>(
                        this.getActivity(), "album", CommentsFragment.class));
        actionBar.addTab(tab);

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

            CommentsService commentsService = new CommentsService();
            ArrayList<Comment> comments = commentsService.execute(token, id_uv).get();

            ((TabActivity) this.getActivity()).setDescription(description);
            ((TabActivity) this.getActivity()).setComments(comments);

        }
        catch(InterruptedException interruptedException)
        {

        }
        catch(ExecutionException executionException)
        {

        }
    }


    @Override
    public void onTabChanged(String s) {

    }
}
