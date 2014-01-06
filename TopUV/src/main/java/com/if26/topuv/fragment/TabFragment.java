package com.if26.topuv.fragment;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
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
 * Fragment qui va afficher sous forme de 3 onglets les 3 fragments suivants :
 * - DescriptionFragment
 * - DétailsFragment
 * - CommentsFragment
 */
public class TabFragment extends Fragment implements OnTabChangeListener {

    private View myView;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        myView = inflater.inflate(R.layout.tab_page, container, false);
        context = this.getActivity();

        // Mise en place des 3 onglets dans l'actionBar
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

            // récupération de la desctipion de l'Uv
            DescriptionDetailsService descriptionService = new DescriptionDetailsService(context);
            Description description = descriptionService.execute(token, id_description).get();

            // récupération des commentaires de l'uv sélectionnée
            CommentsService commentsService = new CommentsService(context);
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
