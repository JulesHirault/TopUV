package com.if26.topuv.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.if26.topuv.R;

/**
 * Created by Flo on 27/12/2013.
 */
public class CommentsFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.comments_page, container, false);
        TextView tv = (TextView) v.findViewById(R.id.commentsTextView);

        return v;
    }
}